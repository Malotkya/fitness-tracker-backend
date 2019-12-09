class App {
    constructor() {
        this.display = document.querySelector("#main");

        this.frmMain = new Main(this);
        this.frmLogin = new Login(this);
        this.frmSignUp = new Signup(this);
        this.frmSettings = new Settings(this);
        this.navBar = new NavBar(this);

        this.clearErrors();
    }

    error = message => {
        let errorBanner = document.createElement("div");
        errorBanner.setAttribute("class", "alert alert-danger alert-dismissible");
        errorBanner.innerText = message;

        let exitButton = document.createElement("button");
        exitButton.setAttribute("type", "button");
        exitButton.setAttribute("class", "close");
        exitButton.setAttribute("data-dismiss", "alert");
        exitButton.innerText = "X";

        errorBanner.appendChild(exitButton);
        document.querySelector("#error").appendChild(errorBanner);
        console.error(message);
    };

    clearErrors = () => {
        document.querySelector("#error").innerHTML = "";
    };

    update = user => {
        let userString = JSON.stringify(user);
        makeRequest("Update", `user=${userString}`).then(user => {
            this.show(user);
        }).catch(e => {
            this.error(e);
        });
    };

    create = () => {
        this.frmSignUp.show();
    };

    settings = () => {
        this.frmSettings.show();
    };

    home = () => {
        this.show();
    };

    show = user => {
        this.login(user);
        if(this.getLoggedIn() === null) {
            this.navBar.showSignUp();
            this.frmLogin.show();
        } else {
            this.navBar.showSignOut();
            this.frmMain.show();
        }
    };

    getLoggedIn = () => {
        let user = sessionStorage.getItem("user");
        if(user === undefined || user === "")
            return null;
        return JSON.parse(user);
    };

    login = user => {
        if( user !== null && user !== undefined )
            sessionStorage.setItem("user", JSON.stringify(user));
    };

    logout = () => {
        sessionStorage.clear();
        this.clearErrors();
        this.show();
    };
}

