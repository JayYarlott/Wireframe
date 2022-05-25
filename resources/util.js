const fs = require('fs');

let a = fs.readFileSync("horse.obj", "utf8");

a = a.split("s\n")
