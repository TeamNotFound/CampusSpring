let facoltaDatalist = document.getElementById("searchFacolta");

const addFacoltaToDatalist = (facoltaName) => {
    let option = document.createElement("option");
    option.value = facoltaName;
    facoltaDatalist.appendChild(option);
}

console.log("Inside search suggestions")

fetch(context+'/api/facolta')
    .then(response => {
        return response.json();
    })
    .then(data => {
        data.forEach((responseFacolta) => {
            addFacoltaToDatalist(responseFacolta.facolta);
        })
    })
    .catch(err => console.log("error: "+ err))

