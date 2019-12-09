class Settings {
    constructor(parent) {
        this.parent = parent;
        this.form = document.querySelector("#settings")
    }

    show = () => {
        this.parent.display.innerHTML = "";
        this.parent.display.appendChild(this.form);
    }
}