class App {
    constructor() {
        this.display = document.querySelector("#main");

        this.frmMain = new Main(this);
        this.frmLogin = new Login(this);
        this.frmSignUp = new Signup(this);
        //this.frmSettings = new Settings(this);
        this.navBar = new NavBar(this);
        this.user = null;

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

    login = () => {
        this.navBar.showSignUp();
        this.frmLogin.show();
    };

    updateLogs = () => {
        makeRequest("Update", "user=" + JSON.stringify(this.user)).then(user => {
            this.main(user);
        }).catch(e => {
            this.error(e);
        })
    };

    updateSettings = () => {
        makeRequest("Update", "user=" + JSON.stringify(this.user)).then(user => {
            this.settings(user);
        }).catch(e => {
            this.error(e);
        })
    };

    logout = () => {
        this.user = null;
        //TODO: do something with the session
        this.login();
    };

    create = () => {
        this.frmSignUp.show();
    };

    delete = () => {

    };

    main = user => {
        this.navBar.showSignOut();
        this.frmMain.show(user);
    };

    settings = user => {
        this.navBar.showSignOut();
        //this.frmSettings.show(user);
    };

    show = () => {
        if(this.user == null)
            this.login();
        else
            this.main();
    }
}

