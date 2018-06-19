const http = require('http');
const mongojs = require('mongojs');
const StaticServer = require('node-static').Server;
const querystring = require('querystring');
const crypto = require('crypto');
const redis = require('redis');
let client = redis.createClient();
let file = new StaticServer('./public');
let db = mongojs('userdb');

let handlers = {};

handlers["/"] = function(req, res) {
	file.serve(req, res);
}

handlers["/agent"] = function(req, res) {
	let qs = querystring.parse(req.url);
	let id = qs['/agent?id'];
	doesSessionExist(id, function(reply) {
		if(reply == 1) {
			const options = {
				hostname: '127.0.0.1',
				port: 8080,
				path: '/actsdb/agent'
			};
			const httpreq = http.request(options);
			httpreq.end();
			httpreq.on('response', function(resData) {
				console.log("------ response event");
				let storeData = "";
				resData.on('data', function(resData) {
					console.log("-------- data event");
					storeData += resData;
				});
				resData.on('end', function() {
					res.writeHead(200, { "Content-type" : "text/plain"});
					res.write(storeData);
					res.end();
				});
			});
		}
		else {
			res.end();
		}
	});

}

handlers["/login"] = function(req, res) {
	let qs = querystring.parse(req.url);
	let un = qs['/login?username'];
	let pw = qs['password'];
	res.writeHead(200, {"Content-Type" : "text/plain"});
	db.users.findOne({ "username" : un, "password" : pw }, (err, docs) => {
		if(docs != null) {
			startSession(un, (id) => {
				res.write(id);
				res.end();
			});
		}
		else {
			res.write("login failed!");
			res.end();
		}
	});
}

function startSession(name, callback) {
	var id = crypto.randomBytes(16).toString("hex");
	client.set(id, name, function() {
		callback(id)
	});
}

function doesSessionExist(sessionid, callback) {
	client.exists(sessionid, function(err, reply) {
		callback(reply)
	});
}

function endSession(sessionid, callback) {
	client.del(sessionid, function(err, reply) {
		callback()
	});
}

const server = http.createServer(function(req, res) {
	console.log(req.url);
	if(handlers[req.url]){
		handlers[req.url](req, res);
	}
	else {
		if(req.url.includes("?")) {
			let url = req.url.split("?");
			handlers[url[0]](req, res);
		}
		else {
			res.writeHead(404);
			res.end();
		}
	}
});

server.listen(1505);
console.log("Node server running...");
