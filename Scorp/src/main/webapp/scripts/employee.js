let employee = {};
let manager = {};

window.onload = function() {
    populateEmployee();
    populateManager();
}

function populateEmployee() {
	//send GET request to SessionServlet to obtain session information
	fetch("http://localhost:8084/ShaneCorp/session").then(function(response) {
		return response.json();
	}).then(function(data) {
		//check whether there was a valid session
		//define behavior for no user returned
		if (data.session === null) {
            window.location = "http://localhost:8084/ShaneCorp/login";
            
		} else {
			//define behavior for user returned
            employee = data;
            console.log(employee);
            
			document.getElementById("manager").placeholder = employee.reportsto;
			document.getElementById("firstname").placeholder = employee.firstname;
			document.getElementById("lastname").placeholder = employee.lastname;
			document.getElementById("email").placeholder = employee.email;
		}
    });
}
function populateManager() {
        //send GET request to SessionServlet to obtain session information
        fetch("http://localhost:8084/ShaneCorp/ManagerEmp").then(function(response) {
            return response.json();
        }).then(function(data) {
            //check whether there was a valid session
            //define behavior for no user returned
            manager = data;
            console.log(manager);
                
            for(let i=0; i< manager.length ;i++){
                let under=document.createElement("tr");
                under.id="row"+i;
                document.getElementById("emp").appendChild(under);

                let eid=document.createElement("td");
                eid.innerText=manager[i].id;
                document.getElementById("row"+i).appendChild(eid);

                let report=document.createElement("td");
                report.innerText=manager[i].reportsto;
                document.getElementById("row"+i).appendChild(report);

                let fn=document.createElement("td");
                fn.innerText=manager[i].firstname;
                document.getElementById("row"+i).appendChild(fn);

                let ln=document.createElement("td");
                ln.innerText=manager[i].lastname;
                document.getElementById("row"+i).appendChild(ln);

                let em=document.createElement("td");
                em.innerText=manager[i].email;
                document.getElementById("row"+i).appendChild(em);

                }
               
            
        });
    }
