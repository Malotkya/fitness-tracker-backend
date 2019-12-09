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

        let userName = this.form.querySelector("#username").value;
        let password1 = this.form.querySelector("#password1").value;
        let password2 = this.form.querySelector("#password2").value;
        let firstName = this.form.querySelector("#firstName").value;
        let lastName = this.form.querySelector("#lastName").value;
        let email = this.form.querySelector("#email").value;

        let errorMessage = "";

        //check if userName is taken in the database.
        // errorMessage += "UserName is already taken.<br />

        if(password1 !== password2)
            errorMessage += "The passwords do not match!\n";

        if(errorMessage === "") {
            this.performSignUp(userName, password1, firstName, lastName, email);
        } else {
            this.parent.error(errorMessage);
            this.enable();
        }

    };

    performSignUp = (userName, password, firstName, lastName,  email) => {
        makeRequest("SignUp", `userName=${userName}&password=${password}&firstName=${firstName}&lastName=${lastName}&email=${email}`)
            .then( user => this.parent.show(user) )
            .catch(e=> this.parent.error(e) );
    };

    show = () => {
        this.enable();
        this.parent.display.innerHTML = "";
        this.parent.display.appendChild(this.form);
    };

    disable = () => {
        this.spinner.setAttribute("style", "display:inline-block");
        this.button.disabled = true;
    };

    enable = () => {
        this.spinner.setAttribute("style", "display:none");
        this.button.disabled = false;
    };
}