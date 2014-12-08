<div class="admin-content">

    <h2 style="text-align: center">${pageTitle}</h2>

    <form accept-charset="UTF-8" class="form" role="form" method="POST">

        <div class="form-group">
            <label for="inpName" class="control-label">Наименование</label>
            <div class="input-group">
                <label class="sr-only" for="inpName">Наименование</label>
                <input type="text" name="nameCat" class="form-control" id="inpName" value="${cat.getName()}"
                       required="required" placeholder="Введите наименование категории">
                <div class="input-group-addon">NAME</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpName" class="control-label">Значение</label>
            <div class="input-group">
                <label class="sr-only" for="inpName">Значение</label>
                <input type="text" name="valCat" class="form-control" id="inpValue" value="${cat.getValue()}"
                       required="required" placeholder="Введите значение категории"/>
                <div class="input-group-addon">NAME</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpMTITLE" class="control-label">META-TITLE</label>
            <div class="input-group">
                <label class="sr-only" for="inpMTITLE">META-TITLE</label>
                <textarea class="form-control" rows="3"  name="MTitleCat" required="required"
                          id="inpMTITLE" placeholder="Введите META TITLE">${cat.getMetaTitle()}</textarea>
                <div class="input-group-addon">META</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpMDESC" class="control-label">META-DESCRIPTION</label>
            <div class="input-group">
                <label class="sr-only" for="inpMDESC">META-DESCRIPTION</label>
                <textarea class="form-control" rows="3" name="MDescCat" id="inpMDESC" required="required"
                          placeholder="Введите META DESCRIPTION">${cat.getMetaDescription()}</textarea>
                <div class="input-group-addon">META</div>
            </div>
        </div>

        <div class="form-group">
            <label for="inpMKey" class="control-label">META-KEYORDS</label>
            <div class="input-group">
                <label class="sr-only" for="inpMKey">META-KEYORDS</label>
                <textarea class="form-control" rows="3" name="MKeyCat" id="inpMKey" required="required"
                          placeholder="Введите META KEYWORDS">${cat.getMetaKeywords()}</textarea>
                <div class="input-group-addon">META</div>
            </div>
        </div>

        <button type="submit" class="btn btn-default">Сохранить</button>

    </form>

</div>
