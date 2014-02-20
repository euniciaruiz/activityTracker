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

function insertRecord() {
    console.debug('called insertRecord()');

	var title = $('#title').val();
    var description = $('#description').val();
    var location = $('#location').val();
    var start_date = $('#start_date').val();
    var end_date = $('#end_date').val();
    var start_time = $('#start_time').val();
    var end_time = $('#end_time').val();
    var priority = $('#priority').val();
    var alert = $('#alert').val();
    var repetition = $('#repetition').val();
    var notification = $('#notification').val();

    var sql = 'INSERT INTO Contacts (title, description, location, start_date, end_date, start_time, end_time,' +
    								'priority, alert, repetition, notification) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)';

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [title, description, location, start_date, end_date, start_time, end_time,
    									 priority, alert, repetition, notification], showRecordsAndResetForm, handleErrors);
            console.debug('executeSql: ' + sql);
        }
    );
}
