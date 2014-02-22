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

    var sql = 'INSERT INTO Activity (title, description, location, start_date, end_date, start_time, end_time,' +
     'priority, alert, repetition, notification) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)';

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [title, description, location, start_date, end_date, start_time, end_time,
     priority, alert, repetition, notification], showRecordsAndResetForm, handleErrors);
            console.debug('executeSql: ' + sql);
        }
    );
}

function resetForm() {
    console.debug('called resetForm()');
    
    $('#title').val('');
    $('#description').val('');
    $('#location').val('');
    $('#start_date').val('');
    $('#end_date').val('');
    $('#start_time').val('');
    $('#end_time').val('');
    $('#priority').val('');
    $('#alert').val('');
    $('#repetition').val('');
    $('#notification').val('');
    $('#id').val('');
}

function showRecordsAndResetForm() {
    console.debug('called showRecordsAndResetForm()');

    resetForm();
    showRecords()
}

function handleErrors(transaction, error) {
    console.debug('called handleErrors()');
    console.error('error ' + error.message);

    alert(error.message);
    return true;
}

function showRecords() {
    console.debug('called showRecords()');

    var sql = "SELECT * FROM Activity";

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [], renderRecords, handleErrors);
        }
    );
}


function renderRecords(transaction, results) {
    console.debug('called renderRecords()');

    html = '';
    $('#results').html('');

    dataset = results.rows;

    if (dataset.length > 0) {
        html = html + ' <ul data-role="listview">';

        for (var i = 0, item = null; i < dataset.length; i++) {
            item = dataset.item(i);

            html = html + ' <li>';
            html = html + ' <h3>' + item['title'] + '</h3>';
            html = html + ' <p>';
            html = html + '<h1>Description: ' + item['description'] + '</h1>';
            html = html + '<h1>Location: ' + item['location'] + '</h1>';
            html = html + '<h1>Start Date: ' + item['start_date'] + '</h1>';
            html = html + '<h1>End Date: ' + item['end_date'] + '</h1>';
            html = html + '<h1>Start Time: ' + item['start_time'] + '</h1>';
            html = html + '<h1>End Time: ' + item['end_time'] + '</h1>';
            html = html + '<h1>Priority: ' + item['priority'] + '</h1>';
            html = html + ' </li>';
        }

        html = html + ' </ul>';

        $('#results').append(html);
        $('#results ul').listview();
    }
}

function loadRecord(i) {
    console.debug('called loadRecord()');

    var item = dataset.item(i);

    $('#title').val(item['title']);
    $('#description').val(item['description']);
    $('#location').val(item['location']);
    $('#start_date').val(item['start_date']);
    $('#end_date').val(item['end_date']);
    $('#start_time').val(item['start_time']);
    $('#end_time').val(item['end_time']);
    $('#priority').val(item['priority']);
}

function prepareAdd() {
  $('form').show();
  $('#btnAdd').addClass('ui-disabled');
  $('#results').addClass('ui-disabled');
  $('#btnSave').on('click', function(){ insertRecord() });
  $('#btnSave').on('click', function(){ cancelAction() });
  $('#name').focus();
}

function cancelAction() {
  $('form').hide();
  $('#btnAdd').removeClass('ui-disabled');
  $('#results').removeClass('ui-disabled');
  $('#btnSave').off('click');
}

function updateCacheContent(event) {
    console.debug('called updateCacheContent()');
    window.applicationCache.swapCache();
}

$(document).ready(function () {
    window.applicationCache.addEventListener('updateready', updateCacheContent, false);

    initDatabase();
    cancelAction();
});