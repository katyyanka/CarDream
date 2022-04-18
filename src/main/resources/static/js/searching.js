function searchFunction() {
    // Объявить переменные
    var input, filter, table, tr, td, i, txtValue, k ;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("example");
    tr = table.getElementsByTagName("tr");
    let th = tr[0].cells.length;


    // Перебирайте все строки таблицы и скрывайте тех, кто не соответствует поисковому запросу
   /* for (i = 0; i < tr.length; i++) {
      //  for (k = 0 ; k < th; k++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
   // }*/

    for (i = 0; i < tr.length; i++) {
        for (k = 0 ; k < th; k++) {
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
