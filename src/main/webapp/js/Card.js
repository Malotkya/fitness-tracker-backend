class Card {
    constructor(parent) {
        this.node = parent.cardTemplate.cloneNode(true);
        this.parent = parent;

    }

    setHeader = header => {
        this.node.querySelector(".card-header").innerText = header;
    };

    addStatic = (title, value) => {
        let p = document.createElement("p");
        p.innerText = title + ": " + value;
        this.node.querySelector(".static").appendChild(p);
    };

    addInput = (title, value) => {
        let input = document.createElement("input");
        input.addEventListener("blur", this.update);
        input.addEventListener("keypress", this.testKeyPress);
        input.value = value;

        let p = document.createElement("p");
        p.innerText = title + ": ";
        p.appendChild(input);

        this.node.querySelector(".variable").appendChild(p);
    };

    addTime = time => {
        this.node.querySelector(".time").innerText = time;
    };

    testKeyPress = event => {
        if(event.keyCode === 13)
            this.update();
    };

    update = () => {
        let time = this.node.querySelector(".time").innerText;
        if(time === "")
            time = new Date().getTime();
        else
            time = Number(time);

        let value = {};
        let submit = false;
        let name = this.node.querySelector(".card-header").innerText;
        let list = [...this.node.querySelector(".variable").querySelectorAll("p")];

        list.forEach(p => {
            let key = p.innerText;
            let item = p.querySelector("input").value;

            console.log(key.substr(0, key.length-2) + ": " + item);

            if(item !== "")
                submit = true;

            value[key.substr(0, key.length-2)] = item;
        });

        let log = {"date":time, "value":value };

        if(submit)
            this.parent.update(name, log);
    }
}