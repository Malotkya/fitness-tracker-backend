class Signup {
    constructor(parent) {
        this.parent = parent;
        this.form = document.querySelector("#sign-up");
        this.button = this.form.querySelector("#submit");

        this.button.addEventListener("click", this.testInputs);
        this.spinner = this.button.querySelector("span");
    };

    testInputs = () => {
        this.disable();

        let userName = this.form.querySelector("#username");
        let password1 = this.form.querySelector("#password1");
        let password2 = this.form.querySelector("#password2");
        let firstName = this.form.querySelector("#firstName");
        let lastName = this.form.querySelector("#lastName");
        let email = this.form.querySelector("#email");

        let errorMessage = "";

        //check if userName is taken in the database.
        // errorMessage += "UserName is already taken.<br />

        if(password1 !== password2)
            errorMessage += "The passwords do not match!<br />";

        if(errorMessage === "") {
            this.performSignUp(userName, password1, firstName, lastName, email);
        } else {
            this.parent.error(errorMessage);
            this.enable();
        }

    };

    performSignUp = (userName, password, firstName, lastName,  email) => {
        console.log("Success!");
    };

    show = () => {
        this.enable();
        this.parent.display.innerHTML = "";
        this.parent.display.appendChild(this.form);
    };

    disable = () => {
        this.spinner.setAttribute("style", "display:inline");
        this.button.disabled = true;
    };

    enable = () => {
        this.spinner.setAttribute("style", "display:none");
        this.button.disabled = false;
    };
}