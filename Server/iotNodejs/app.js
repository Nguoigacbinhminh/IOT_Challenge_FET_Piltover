//init to connect to database via node js
var mysql = require('mysql')
var con = mysql.createConnection({
    host: "localhost",
    // port: '/var/run/mysqld/mysqld.sock',
    user: "root",
    password: "Dphps278",
    database: "F1manager"
})





//init node js to communicate via uart 
const { SerialPort } = require('serialport')
const { ReadlineParser } = require('@serialport/parser-readline')   
const port = new SerialPort({path:'/dev/ttyACM0', baudRate:115200})

const parser = port.pipe(new ReadlineParser({delimiter:'\r\n'}))
port.on("open", ()=> {
    console.log('port opened!\n')
});


con.connect(function(err){
    if (err) throw err;
    console.log("Connected to database!\n");
    parser.on('data', data=>to_database(data))
})


//send data to server
the_count = 0;
hr = 0;
spo2 = 0;
temp = 0;
function to_database(string,the_count){
 
    if(string.startsWith('HR')){
        temp_string = string.replace('HR ','')
        hr = Number(temp_string)
        // hr = hr/80
        // console.log('ir checked!')
        
    }

    if(string.startsWith('SPO2')){
        temp_string = string.replace('SPO2 ','')
        spo2 = Number(temp_string)
        spo2 = spo2/100
        // console.log('red checked!')
        
    }

    if (string.startsWith('Temp')){
        temp_string = string.replace('Temp ','')
        temp = Number(temp_string)
        temp = temp/100
        //send to database
        if(hr>=0 && spo2 >=0){
            console.log('to database checked!')
            console.log(hr+' '+spo2+' '+temp)
            var time = new Date().toISOString().replace(/T/, ' ').replace(/\..+/, '')
            console.log(time);

            var sql = ("UPDATE RESULT SET time ='"+time+"', heart_rate="+hr+", temp="+temp+",spo2="+spo2+" WHERE id = 1");
                console.log(sql)
                con.query(sql, function (err, result) {
                  if (err) throw err;
                  console.log(result.affectedRows + " record(s) updated");
                });


        }
        

    }
    
}
// parser.on('data', data=>to_database(data))