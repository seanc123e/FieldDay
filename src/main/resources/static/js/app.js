document.addEventListener("DOMContentLoaded", (event) => {
    // LOGIN PAGE
    if (window.location.pathname.split("/").at(-1) == "login.html") {
        let loginForm = document.getElementById("loginForm");
        loginForm.addEventListener("submit", function (e) {
            e.preventDefault(); //preventing defualt refresh on forms so that the data can stick
            document.cookie = "loggedIn";
            console.log(document.cookie);
            window.location.replace("index.html");
        })
    }

    //HOME PAGE ADDING PROFILE ICON ON LOGIN
    if ((window.location.pathname.split("/").at(-1) == "index.html" && document.cookie == "loggedIn") || (window.location.pathname.split("/").at(-1) == "myEvents.html" && document.cookie == "loggedIn") || (window.location.pathname.split("/").at(-1) == "createEvent.html" && document.cookie == "loggedIn")) {
        console.log(document.cookie);
        let loginSignupBtn = document.getElementById("loginSignupBtn");
        console.log(loginSignupBtn);
        loginSignupBtn.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/></svg>';
        loginSignupBtn.innerHTML += "<button type ='button' class='btn btn-outline-dark mx-2'>Sign out</button>"
        loginSignupBtn.setAttribute("href", "");
        //user logout button
        loginSignupBtn.addEventListener("click", function () {
            document.cookie = "loggedOut";
            loginSignupBtn.innerHTML = "Logout";
        })
    }

    //MY EVENTS PAGE
    if (window.location.pathname.split("/").at(-1) == "myEvents.html") {
        let myEventsHero = document.getElementById("myEventsHero");
        let myEventsTableBody = document.getElementById("myEventsTableBody");


        //fetch request
        fetch("http://localhost:3000/events")
            .then((resp) => resp.json()) //returns javascript object by taking the JSON input
            .then((events) => {
                for (const event of events) {
                    //creating table row to append data to DOM
                    const eventsTableRow = document.createElement("tr");
                    myEventsTableBody.append(eventsTableRow);

                    //creating elements for table rows
                    const eventsNameData = document.createElement("td"); //table data cell for event name
                    const eventsDateData = document.createElement("td"); // table data cell for event date
                    const eventsLocationData = document.createElement("td"); //table data cell for event location
                    const eventsHostData = document.createElement("td"); //table data cell for event host
                    //grabbing data from JSON db
                    eventsNameData.innerHTML = event.title;
                    eventsDateData.innerHTML = event.date;
                    eventsLocationData.innerHTML = event.location;
                    eventsHostData.innerHTML = event.host;
                    //adding the data from json db
                    eventsTableRow.append(eventsNameData);
                    eventsTableRow.append(eventsDateData);
                    eventsTableRow.append(eventsLocationData);
                    eventsTableRow.append(eventsHostData);
                    //ask about how to format datetime-local type to make it more readable for the user
                }
            })
    }

    // CREATE EVENTS PAGE
    if (window.location.pathname.split("/").at(-1) == "createEvent.html") {

        createEventForm.addEventListener("submit", (e) => {
            e.preventDefault();
            //grabbing data values from user input to add to the JSON db
            const createEventName = document.getElementById("createEventName").value;
            const createEventDescription = document.getElementById("createEventDescription").value;
            const createEventLocation = document.getElementById("createEventLocation").value;
            const createEventDateTime = document.getElementById("createEventDateTime").value;
            const createEventHost = document.getElementById("createEventHost").value;
            const createEventPrice = document.getElementById("createEventPrice").value;
            const data = {
                title: createEventName,
                description: createEventDescription,
                location: createEventLocation,
                date: createEventDateTime,
                price: createEventPrice,
                host: createEventHost,
                image: "https://placehold.co/400"
            }

            fetch("http://localhost:3000/events", {
                method: "POST", //http request to add a record to the database
                headers: {
                    "Content-Type": "application/json", //setting more properties about the http request. it allows the server to expect data in a JSON format and handle it properly
                },
                body: JSON.stringify(data)//makes the JS object readable to JSON
            })
            .then((resp) => resp.json()) //returns the JSON input as a JS object
            .then((data) => console.log(data)) //unnecessary, but nice to see what is actively happening in the console
        })
    }


})