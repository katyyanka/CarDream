function searchFunction() {
    // Объявить переменные
    var input, filter, table, tr, td, i, txtValue, k;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("example");
    tr = table.getElementsByTagName("tr");
    let th = tr[0].cells.length;

    for (i = 0; i < tr.length; i++) {
        for (k = 0; k < th; k++) {
            td = tr[i].getElementsByTagName("td")[k];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    break
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
}

function searchFunction2() {
    // Объявить переменные
    var input, filter, table, tr, td, i, txtValue, k;
    input = document.getElementById("myInput2");
    filter = input.value.toUpperCase();
    table = document.getElementById("example2");
    tr = table.getElementsByTagName("tr");
    let th = tr[0].cells.length;

    for (i = 0; i < tr.length; i++) {
        for (k = 0; k < th; k++) {
            td = tr[i].getElementsByTagName("td")[k];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    break
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
}

function filterOrderPlaceFunction() {
    var input1, input2, table1, table2, tr, td, i;
    input1 = document.getElementById("filterInput1");
    let filter1 = input1.value.toUpperCase();
    input2 = document.getElementById("filterInput2");
    let filter2 = input2.value.toUpperCase();
    table1 = document.getElementById("example");
    table2 = document.getElementById("example2");
    tr = table1.getElementsByTagName("tr");

    // Перебирайте все строки таблицы и скрывайте тех, кто не соответствует поисковому запросу
    for (i = 0; i < tr.length; i++) {
        let td1 = tr[i].getElementsByTagName("td")[1];
        let td2 = tr[i].getElementsByTagName("td")[2];
        if (td1 && td2) {
            let txtValue1 = td1.textContent || td1.innerText;
            let txtValue2 = td2.textContent || td2.innerText;
            if (txtValue1.toUpperCase().indexOf(filter1) > -1 && txtValue2.toUpperCase().indexOf(filter2) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
    try {
        tr = table2.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            let td1 = tr[i].getElementsByTagName("td")[1];
            let td2 = tr[i].getElementsByTagName("td")[2];
            if (td1 && td2) {
                let txtValue1 = td1.textContent || td1.innerText;
                let txtValue2 = td2.textContent || td2.innerText;
                if (txtValue1.toUpperCase().indexOf(filter1) > -1 && txtValue2.toUpperCase().indexOf(filter2) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    } catch (e) {

    }
}

function filterFunction() {
    // Объявить переменные
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filterInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("example");
    tr = table.getElementsByTagName("tr");

    // Перебирайте все строки таблицы и скрывайте тех, кто не соответствует поисковому запросу
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
