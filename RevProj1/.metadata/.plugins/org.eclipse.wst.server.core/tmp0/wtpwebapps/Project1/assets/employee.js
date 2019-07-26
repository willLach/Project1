var baseURL = 'http://localhost:8080/Project1/';
var empInfo = [];
document.getElementById("req-submit").onclick = () =>
{
    let xhr = new XMLHttpRequest();
    xhr.open("POST", baseURL + "api/request");
    let request = {};
    request.amount = document.getElementById("reim-amount").value;
    request.uid = '1';
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4)
        {
            if(xhr.status === 201 || xhr.status === 200)
            {

            }
        }
    };
    xhr.send(JSON.stringify(request));
};

document.getElementById("view-info").onclick = () =>
{
    console.log("i did thing");
    let xhr = new XMLHttpRequest();
    xhr.open("GET", baseURL + "api/user");
    xhr.onreadystatechange = () => {
        console.log(xhr.readyState);
        if(xhr.readyState === 4)
        {
            console.log(xhr.status);
            if(xhr.status === 200)
            {
                let resp = JSON.parse(xhr.response);
                empInfo = [];
                for(let data of resp)
                {
                    empInfo.push(data);
                }
                popDataTable();
            }
        }
    };
    xhr.send();
};

document.getElementById("change-info").onclick = () => {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", baseURL + "api/user");
    let user = {};
    //user.userId = 1;
    //user.username = 'LeeJenk';
    //console.log(document.getElementById('first-name').value);
    user.firstName = document.getElementById('first-name').value;
    //console.log("Username = " + user.firstName);
    user.lastName = document.getElementById('last-name').value;
    user.address = document.getElementById('address').value;
    user.gender = document.getElementById('gender').value;
    console.log(user);
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4)
        {
            console.log(xhr.status);
            if(xhr.status === 201 || xhr.status === 200)
            {
                document.getElementById('first-name').value = '';
                document.getElementById('last-name').value = '';
                document.getElementById('address').value = '';
                document.getElementById('gender').value = 'M';
                
            }
        }
    };
    xhr.send(JSON.stringify(user));
};