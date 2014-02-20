var db;
var dataset;

function initDatabase() {
    console.debug('called initDatabase()');

    try {
        if (!window.openDatabase) {
            alert('not supported');
        } else {
            var shortName = 'ActivityTracker';
            var version = '1.0';
            var displayName = 'ActivityTracker';
            var maxSizeInBytes = 65536;
            db = openDatabase(shortName, version, displayName, maxSizeInBytes);

            createTableIfNotExists();
        }
    } catch(e) {
        if (e == 2) {
            alert('Invalid database version');
        } else {
            alert('Unknown error ' + e);
        }
        return;
    }
}

function createTableIfNotExists() {
    console.debug('called createTableIfNotExists()');

    var sql = "CREATE TABLE IF NOT EXISTS Activity (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT UNIQUE NOT NULL," +
    		  "description TEXT, location TEXT, start_date DATE, end_date DATE, start_time DATETIME, end_time DATETIME," +
    		  "priority TEXT, alert TEXT NOT NULL, repetition TEXT, notification TEXT)";

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [], showRecords, handleErrors);
            console.debug('executeSql: ' + sql);
        }
    );
}
