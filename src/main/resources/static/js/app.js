document.addEventListener("DOMContentLoaded", (event) => {

// JavaScript function to load new random events
if (window.location.pathname.split("/").at(-1) == "home") {
    function loadRandomEvents() {
      fetch('http://localhost:8081/home')
        .then(response => response.text())
        .then(events => {
          const eventsContainer = document.getElementByClassName('eventsContainer');
          eventsContainer.innerHTML = '';

          events.forEach(event => {
          const eventCardTemplate = `<div th:replace="_event_card :: _event_card" th:with="event=${event}"></div>`;
          const tempElement = document.createElement('div');
          tempElement.innerHTML = eventCardTemplate.trim();
          eventsContainer.appendChild(tempElement.firstChild);
          })
        })
        .catch(error => console.error('Error fetching random events:', error));
    }
    // Attach a click event listener to the "See More" button
    console.log("TOAST");
    document.getElementById('seeMoreButton').addEventListener('click', loadRandomEvents);
}


})
