
  <div class="admin-content">

    <table class="table rowHower">
      <tr>
          <td>ID</td>
          <td>Наименование</td>
          <td>Категория</td>
          <td>Цена</td>
          <td>Количество</td>
          <td>Продано</td>
          <td>Изображение</td>
      </tr>

      <c:forEach items="${requestScope.goods}" var="good">
          <tr style="cursor: pointer; " al onclick="location.href='${URL}/admin/goods/edit?id=${good.getId()}'">
          <td>${good.getId()}</td>
          <td>${good.getName()}</td>
          <td>${good.getCategory().getName()}</td>
          <td>${good.getPrice()}</td>
          <td>${good.getCountAvailable()}</td>
          <td>${good.getCountSold()}</td>
          <td>
            <c:forEach items="${good.getImages()}" var="image">
              <img style="width:40px; height: 40px;" src="${image.getUrl()}">
            </c:forEach>
          </td>
        </tr>
      </c:forEach>
    </table>



  </div>