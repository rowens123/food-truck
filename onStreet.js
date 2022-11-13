const express = require('express');
const fetch = require("node-fetch");
const { response } = require('express');
const app = express();
const port = 8080;
app.set('json spaces', 2);
let availableStreet = [];
//const GitHub = require('github-api');
function sleep(milliseconds)
{
    const date = Date.now();
    let currentDate = null;
    do { 
        currentDate = Date.now();
    } while (currentDate - date < milliseconds);
}


app.get('/getData', async function (req, res) {

    res.json({
        output: availableStreet,
    });
})

//Starting server using listen function
app.listen(port, function () {
    console.log("Server has been started at " + port);
})



var myHeaders = new Headers();

setInterval(callAPI, 20000);

function callAPI()
{
    fetch("http://localhost:8000/gettoken").then(response => response.json())
.then((result) => {
    const token = result.token;
    console.log("loop");
    myHeaders.append("Authorization", "Bearer " + token);

    var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
    };
    fetch("https://api.iq.inrix.com/blocks/v3?point=37.74304518280319%7C-122.42438793182373&radius=1000", requestOptions)
    .then(response => response.json())
    .then(spots => {
        
        var i = 0;
        availableStreet = [];
        let unavailableStreet = [];
        console.log(spots.result[0].geojson.coordinates[1][0]);
        while(typeof spots.result[i] !== 'undefined'){
            var spotEmpty = 0;
            var j = 0;
            while (typeof spots.result[i].segments[j] !== 'undefined') {
                if(spots.result[i].segments[j].isOpen === true)
                {
                    spotEmpty++;
                }
                j++;
                
            }
            if (spotEmpty > 0) {
                //if(spots.result[i].probability>75) {
                    //var needToPay = false;
                    availableStreet.push([spots.result[i].distance, spots.result[i].probability,
                        spots.result[i].name + " " + spots.result[i].address,spots.result[i].geojson.coordinates[1][1],spots.result[i].geojson.coordinates[1][0]]);
                //}
            }
            else {
                unavailableStreet.push(spots.result[i].name + " " + spots.result[i].address);
            }
                
            i++;
        }
        availableStreet.sort(function(a,b){return b[1] - a[1]}).sort(function(a,b){return a[0] - b[0]});
        //console.log("Available streets:");
        //console.log(availableStreet.join(','));
        //console.log("Unavailable streets:");
        //console.log(unavailableStreet);





        
    
    })

    .catch(error => console.log('error', error));
})
}