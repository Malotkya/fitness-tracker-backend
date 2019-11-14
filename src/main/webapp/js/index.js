const updateTime = 3600000 * 3; // 3 hours

let init  = () => {
    let app = new App();
    app.show();
}; window.onload = init;

const makeRequest = (url, parameters) => {
    return new Promise((resolve, reject) => {
        let xhr = new XMLHttpRequest();

        xhr.open("POST", url);

        xhr.onreadystatechange = () => {
            if(xhr.readyState === 4) {
                if(xhr.status === 200) {
                    resolve(JSON.parse(xhr.responseText));
                } else {
                    reject(new Error(xhr.responseText));
                }
            }
        };

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(parameters);
    });
};

