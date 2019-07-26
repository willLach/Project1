var baseURL = 'http://localhost:8080/Project1/';
var requests = [];
var emps = [];
document.getElementById("emp-load").onclick = () =>
{
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "api/user");
    xhr.onreadystatechange = () => {
        console.log(xhr.readyState);
        if(xhr.readyState === 4)
        {
            console.log(xhr.readyState);
            console.log(xhr.status);
            if(xhr.status === 200)
            {
                let resp = JSON.parse(xhr.response);
                console.log(resp);
                emps = [];
                for(let user of resp)
                {
                    console.log(user);
                    emps.push(user);
                }
                popEmpTable();
            }
        }
    };
    xhr.send();
};
document.getElementById("load").onclick = () => {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "api/request");
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4)
        {
            if(xhr.status === 200)
            {
                let resp = JSON.parse(xhr.response);
                
                requests = [];
                for(let reimReq of resp)
                {
                    console.log(reimReq);
                    requests.push(reimReq);
                }
                popReqTable();
            }
        }
    };
    xhr.send();
};

function popReqTable(){
    for(let req of requests)
    {
        let newRow = document.createElement('tr');

        let idCol = document.createElement('td');
        idCol.innerText = req.id;
        let nameCol = document.createElement('td');
        nameCol.innerText = req.u_id;
        let genCol = document.createElement('td');
        genCol.innerText = req.amount;
        let addCol = document.createElement('td');
        addCol.innerText = req.recImg;

        newRow.appendChild(idCol);
        newRow.appendChild(nameCol);
        newRow.appendChild(genCol);
        newRow.appendChild(addCol);
        document.getElementById('request-table').appendChild(newRow);
    }

}

function popEmpTable(){
    for(let user of emps)
    {
        let newTable = document.createElement('table');
        console.log(user.uid);
        let newRow = document.createElement('tr');

        let idCol = document.createElement('td');
        
        idCol.innerText = user.uid;
        let nameCol = document.createElement('td');
        nameCol.innerText = user.fname + user.lname;
        let genCol = document.createElement('td');
        genCol.innerText = user.gender;
        let addCol = document.createElement('td');
        addCol.innerText = user.address;

        newRow.appendChild(idCol);
        newRow.appendChild(nameCol);
        newRow.appendChild(genCol);
        newRow.appendChild(addCol);
        newTable.appendChild(newRow);
    }
}
function resetTable(){
    let table = document.getElementById('request-table');
    while(table.firstChild)
    {
        table.removeChild(table.firstChild);
    }

}