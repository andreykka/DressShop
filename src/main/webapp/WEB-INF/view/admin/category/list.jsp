<div class="admin-content">

  <div class="table-responsive">
    <table class="table rowHower">
      <tr>
        <td>ID</td>
        <td>Название</td>
        <td>Значение</td>
        <td>Удалить</td>
      </tr>

      <c:forEach items="${requestScope.categories}" var="cat">
        <c:set var="actionClick" value="location.href='${URL_ADMIN}/category/edit?id=${cat.getId()}'"/>
        <tr>
          <td style="cursor: pointer;" onclick="${actionClick}"> ${cat.getId()}</td>
          <td style="cursor: pointer;" onclick="${actionClick}"> ${cat.getName()}</td>
          <td style="cursor: pointer;" onclick="${actionClick}"> ${cat.getValue()}</td>

          <td style="cursor: pointer;" onclick="location.href='${URL_ADMIN}/category/remove?id=${cat.getId()}'">
            <img src="${URL}/images/remove.ico" style="width:35px; height: 35px;"/>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>



</div>