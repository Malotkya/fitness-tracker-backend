class NavBar {
    constructor(parent) {
        this.parent = parent;

        this.loggedOut = [...document.querySelectorAll(".btn-logged-out")];
        this.loggedIn = [...document.querySelectorAll(".btn-logged-in")];

        document.querySelector("#Home").addEventListener("click", this.parent.home);
        document.querySelector("#SignUp").addEventListener("click", this.parent.create);
        document.querySelector("#SignOut").addEventListener("click", this.parent.logout);
        document.querySelector("#Settings").addEventListener("click", this.parent.settings);
    }

    showSignUp() {
        this.loggedOut.forEach(button => {button.setAttribute("style", "display:inline-block")});
        this.loggedIn.forEach(button => {button.setAttribute("style", "display:none")});
    }

    showSignOut() {
        this.loggedOut.forEach(button => {button.setAttribute("style", "display:none")});
        this.loggedIn.forEach(button => {button.setAttribute("style", "display:inline-block")});
    }
}