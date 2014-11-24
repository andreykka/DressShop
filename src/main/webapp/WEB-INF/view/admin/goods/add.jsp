
    <div class="admin-content">
    <h2 style="text-align: center">Добавление нового товара</h2>

    <form id="addTovarForm" class="form" role="form" method="POST" enctype="multipart/form-data">

        <div class="form-group">
            <label for="inpName" class="control-label">Наименование</label>
            <div class="input-group">
                <label class="sr-only" for="inpName">Наименование</label>
                <input type="text" name="nameG" class="form-control" id="inpName"  required="required" placeholder="Введите наименование товара">
                <div class="input-group-addon">NAME</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpDesc" class="control-label">Описание</label>
            <div class="input-group">
                <label class="sr-only" for="inpDesc">Описание</label>
                <textarea class="form-control" rows="3" name="descG" id="inpDesc" required="required" placeholder="Введите описание товара"></textarea>
                <div class="input-group-addon"><span class="glyphicon glyphicon-pencil"></span> </div>
            </div>
            <%--<input type="text" name="descG" class="form-control" id="inpDesc"  required="required" placeholder="Введите наименование товара">--%>
        </div>

        <div class="form-group">
            <label for="inpCat" class="control-label">Категория</label>
            <%--<div class="input-group">--%>
                <select class="form-control" id="inpCat" name="categoryG">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">
                        ${category.name}
                    </option>
                </c:forEach>
                <%--<div class="input-group-addon">&nbsp;<span class="glyphicon glyphicon-pushpin">&nbsp;</span></div>--%>
                </select>
            <%--</div>--%>
        </div>


        <div class="form-group">
            <label for="inpCount" class="control-label">Количество</label>
            <input type="text" class="form-control" name="countG" id="inpCount" placeholder="Введите количество товаров" required="required" pattern="^[1-9]{1}[0-9]{0,9}$">
        </div>

        <div class="form-group">
            <label for="inpPrice" class="control-label">Цена</label>
            <div class="input-group">
                <label class="sr-only" for="inpPrice">Цена</label>
                <input type="text" class="form-control" name="priceG" id="inpPrice" required="required" placeholder="Введите цену за товар">
                <div class="input-group-addon">&nbsp;UAN&nbsp;&nbsp;</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpMTITLE" class="control-label">META-TITLE</label>
            <div class="input-group">
                <label class="sr-only" for="inpMTITLE">META-TITLE</label>
                <textarea class="form-control" rows="3"  name="MTitleG" required="required" id="inpMTITLE" placeholder="Введите META TITLE"></textarea>
                <div class="input-group-addon">META</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpMDESC" class="control-label">META-DESCRIPTION</label>
            <div class="input-group">
                <label class="sr-only" for="inpMDESC">META-DESCRIPTION</label>
                <textarea class="form-control" rows="3" name="MDescG" id="inpMDESC" required="required" placeholder="Введите META DESCRIPTION"></textarea>
                <div class="input-group-addon">META</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpMKey" class="control-label">META-KEYORDS</label>
            <div class="input-group">
                <label class="sr-only" for="inpMKey">META-KEYORDS</label>
                <textarea class="form-control" rows="3" name="MKeyG" id="inpMKey" required="required" placeholder="Введите META KEYWORDS"></textarea>
                <div class="input-group-addon">META</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpFile">Выберите файл</label>
            <input type="file" accept="image/*" id="inpFile" name="inputFile">
            <p class="help-block">Выберите изображение в формате (.jpg .jpeg .png .gif )</p>
        </div>

        <button type="submit" class="btn btn-default">Сохранить</button>

    </form>

    </div>
