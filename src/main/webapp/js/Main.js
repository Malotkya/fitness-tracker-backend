class Main {
    constructor(parent) {
        this.parent = parent;
        this.cardTemplate = document.querySelector(".card");
    }

    show = user => {
        if(user != null)
            this.parent.user = user;

        this.parent.display.innerText = "";
        this.parent.user.Activities.forEach(activity => {
            this.parent.display.appendChild(this.buildActivityCard(activity).node);
        });
    };

    buildActivityCard = object => {
        let stats = this.parent.user[object.name];
        let logs = object.logs;

        let staticList = object.settings.filter(item => item.Static);
        let logList = object.settings.filter(item => !item.Static);

        let card = new Card(this);
        card.setHeader(object.name);

        for(let i=0; i<staticList.length; i++) {
            let setting = staticList[i];
            card.addStatic(setting.Value, stats[setting.Value]);
        }

        let recent = logs[0];
        for(let i=1; i<logs.length; i++) {
            if(recent.date < logs[i].date)
                recent = logs[i];
        }

        let test = ((new Date().getTime()) - recent.date) <= updateTime;

        if(test)
            card.addTime(recent.date);


        for(let i=0; i<logList.length; i++) {
            let setting = logList[i];

            if(test) {
                card.addInput(setting.Value, recent.value[setting.Value]);
            } else {
                card.addInput(setting.Value, "");
            }
        }

        return card;
    };

    update = (name, log) => {
        let activities = this.parent.user.Activities;
        let logs = [];

        activities.forEach(activity => {
            if(activity.name === name)
                logs = activity.logs;
        });

        let newLog = true;
        logs.forEach(item => {
            if(item.date === log.date ) {
                newLog = false;
                item.value = log.value;
            }
        });

        if(newLog)
            logs.push(log);

        console.log(JSON.stringify(this.parent.user));
        this.parent.updateLogs();
    }
}
