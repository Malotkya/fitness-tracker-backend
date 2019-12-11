class Settings {
    constructor(parent) {
        this.parent = parent;
        this.form = document.querySelector("#settings");
        this.list = document.querySelector("#logList");
    }

    show = () => {
        this.parent.display.innerHTML = "";
        this.parent.display.appendChild(this.form);

        this.list.innerHTML = "";
        let user = this.parent.getLoggedIn();
        user.Activities.forEach(activity => {
            activity.logs.forEach(log => {
                let li = document.createElement("li");
                li.innerText = `${activity.name}: ${new Date(log.date)} - ${JSON.stringify(log.value)}`;
                li.setAttribute("ActivityId", activity.id);
                li.setAttribute("Date", log.date);

                let button = document.createElement("button");
                button.addEventListener("click", this.removeLog);
                button.innerText = "Remove Log";

                li.appendChild(button);
                this.list.appendChild(li);
            });
        });

    };

    removeLog = event => {
        let li = event.currentTarget.parentNode;
        let activityId = li.getAttribute("ActivityId");
        let log = li.getAttribute("Date");

        let user = this.parent.getLoggedIn();
        let activities = user.Activities;
        for(let i=0; i<activities.length; i++) {
            if(activities[i].id == activityId) {
                let logs = activities[i].logs;
                for(let u=0; u<logs.length; u++) {
                    if(logs[u].date == log) {
                        user.Activities[i].logs.splice(u,1);
                    }
                }
            }
        }

        makeRequest("Update", `user=${JSON.stringify(user)}`).then(user => {
            this.parent.show(user);
        }).catch(e=> {
            this.parent.error(e);
        })
    }
}