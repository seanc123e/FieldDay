document.addEventListener("DOMContentLoaded", (event) => {
console.log("coffee")
// JavaScript function to load new random events
if (window.location.pathname.split("/").at(-1) == "home") {
    function loadRandomEvents() {
      fetch('http://localhost:8081/shuffle')
        .then(response => response.json())
        .then(events => {
            console.log(events);
            const eventTitles = document.getElementsByClassName("eventTitle");
            const eventDates = document.getElementsByClassName("eventDate");
            const eventLocations = document.getElementsByClassName("eventLocation");
            const eventUrls = document.getElementsByClassName("eventUrl");
            for(let i = 0; i < 3; i++){
                eventTitles[i].innerHTML = events[i].title;
                eventDates[i].innerHTML = events[i].date;
                eventLocations[i].innerHTML = events[i].location;
                eventUrls[i].href = '/event/' + events[i].eventId;
            }

          })
          .catch(error => console.error('Error fetching random events:', error.message));
        }
    // Attach a click event listener to the "See More" button
        console.log("TOAST");
        document.getElementById('seeMoreButton').addEventListener('click', loadRandomEvents);
}

})
