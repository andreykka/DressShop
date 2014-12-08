  <div class="admin-content">

      <div class="table-responsive">
        <table class="table rowHower">
          <tr >
              <td>ID</td>
              <td>Наименование</td>
              <td>Категория</td>
              <td>Цена</td>
              <td>Количество</td>
              <td>Продано</td>
              <td>Изображение</td>
              <td>Удалить</td>
          </tr>

          <%--URL_ADMIN invoke in header.jspf--%>
          <c:forEach items="${requestScope.goods}" var="good">
              <tr>
              <td style="cursor: pointer; "onclick="location.href='${URL_ADMIN}/goods/edit?id=${good.getId()}'">${good.getId()}</td>
              <td style="cursor: pointer; "onclick="location.href='${URL_ADMIN}/goods/edit?id=${good.getId()}'">${good.getName()}</td>
              <td style="cursor: pointer; "onclick="location.href='${URL_ADMIN}/goods/edit?id=${good.getId()}'">${good.getCategory().getName()}</td>
              <td style="cursor: pointer; "onclick="location.href='${URL_ADMIN}/goods/edit?id=${good.getId()}'">${good.getPrice()}</td>
              <td style="cursor: pointer; "onclick="location.href='${URL_ADMIN}/goods/edit?id=${good.getId()}'">${good.getCountAvailable()}</td>
              <td style="cursor: pointer; "onclick="location.href='${URL_ADMIN}/goods/edit?id=${good.getId()}'">${good.getCountSold()}</td>
              <td style="cursor: pointer; "onclick="location.href='${URL_ADMIN}/goods/edit?id=${good.getId()}'">
                <c:forEach items="${good.getImages()}" var="image">
                  <img style="width:40px; height: 40px;" src="${URL}${image.getUrl()}">
                </c:forEach>
              </td>
              <td style="cursor: pointer; "onclick="location.href='${URL_ADMIN}/goods/remove?id=${good.getId()}'">
                  <img src="${URL}/images/remove.ico" style="width:40px; height: 40px;"/>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>



  </div>