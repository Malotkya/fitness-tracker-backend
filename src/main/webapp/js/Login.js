class Login {
    constructor(parent) {
        this.form = document.querySelector("#login");
        this.parent = parent;
        this.button = this.form.querySelector("#submit");

        this.button.addEventListener("click", this.performLogin);
    }

    performLogin = () => {
        let username = document.querySelector("#username").value;
        let password = document.querySelector("#password").value;

        this.button.innerText = "";
        this.button.innerHTML = "<span class='spinner-border spinner-border-sm'></span> Login";

        makeRequest("Login", "username=" + username + "&password=" + password).then(user => {
            this.parent.main(user);
        }).catch(e => {
            this.parent.error(e);
        });
    };

    show = () => {
        this.button.innerHTML = "";
        this.button.innerText = "Login";
        this.parent.display.innerHTML = "";
        this.parent.display.appendChild(this.form);
    }
}