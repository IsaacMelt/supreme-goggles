function deleteRow() {
    var programIdToDelete = prompt("Введите ID программы, которую нужно удалить.");
    // строка с параметрами для отправки
    var parameters = "Program_ID=" + programIdToDelete;

    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8081/webp/DeleteProgram");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    request.send(parameters);
    window.location.reload(false);
}