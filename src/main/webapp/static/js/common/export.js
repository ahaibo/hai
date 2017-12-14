(function () {
    var _id, _fileName;
    var ActiveXObjectExportUtil = {
        init: function (id, fileName) {
            _id = id;
            _fileName = fileName;
            return this;
        },
        export: function () {
            var table = document.getElementById(_id);
            if (!table.length)return;
            var rows = table.rows.length, columns = table.rows(1).cells.length;

            var wdapp = new ActiveXObject("Word.Application");
            wdapp.visible = true;

            var wddoc = wdapp.Documents.Add();//add new document
            var tharray = [];//将页面表格内的内容放到数组中
            for (var i = 0; i < rows; i++) {
                tharray[i] = [];
                for (var j = 0; j < columns; j++) {
                    tharray[i][j] = table.rows(i).cells(j).innerHTML;
                }
            }

            var range = wddoc.Range(0, 0);
            range.Text = "订单信息列表\n";

            wdapp.Application.Activedocument.Paragraphs.Add(range);
            wdapp.Application.Activedocument.Paragraphs.Add();
            var currRange = wdapp.Application.Activedocument.Paragraphs(3).Range;

            var objTable = wddoc.Tables.Add(currRange, rows, columns);//插入表格
            for (var i = 0; i < rows; i++) {
                for (var j = 0; j < columns; j++) {
                    objTable.Cell(i + 1, j + 1).Range.Text = tharray[i][j].replace('&nbsp;', '');
                }
            }

            wdapp.Application.ActiveDocument.SaveAs(_fileName, 0, false, '', true, '', false, false, false, false, false);
            wdapp.Application.Printout();
            wdapp = null;
        }
    }

    window.ActiveXObjectExportUtil = ActiveXObjectExportUtil;
})();