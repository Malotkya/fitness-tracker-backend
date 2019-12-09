class Login {
    constructor(parent) {
        this.form = document.querySelector("#login");
        this.parent = parent;
        this.button = this.form.querySelector("#submit");
        this.spinner = this.button.querySelector("span");
    }

    performLogin = () => {
        this.disable();
        let username = document.querySelector("#username").value;
        let password = document.querySelector("#password").value;

        makeRequest("Login", `username=${username}&password=${password}`).then(user => {
            this.parent.show(user);
        }).catch(e => {
            this.parent.error(e);
            this.enable();
        });
    };

    show = () => {
        this.enable();
        this.parent.display.innerHTML = "";
        this.parent.display.appendChild(this.form);
    };

    disable = () => {
        this.spinner.setAttribute("style", "display:inline-block");
        this.button.removeEventListener("click", this.performLogin);
    };

    enable = () => {
        this.spinner.setAttribute("style", "display:none");
        this.button.addEventListener("click", this.performLogin);
    };
}