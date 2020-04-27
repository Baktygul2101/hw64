'use strict';
window.addEventListener('load', function () {

    const user = fetch("http://localhost:8181/demo/getUser");
    console.log(user);

    const saveButton = document.getElementById("save-candidate");
    saveButton.addEventListener("click", function() {
        const candidateForm = document.getElementById("form");
        let data = new FormData(candidateForm);

        fetch("http://localhost:8181/candidate", {
            method: 'POST',
            body: data
        }).then(r => r.json()).then(data => {window.location.href = "http://localhost:8181/"});
    });

});
