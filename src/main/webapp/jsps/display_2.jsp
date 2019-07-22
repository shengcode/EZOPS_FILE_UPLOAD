<c:if test="${paginationProducts.totalPages > 1}">
   <div class="page-navigator">
      <c:forEach items="${paginationProducts.navigationPages}" var = "page">
         <c:if test="${page != -1 }">
            <a href="productList?page=${page}" class="nav-item">${page}</a>
         </c:if>
         <c:if test="${page == -1 }">
            <span class="nav-item"> ... </span>
         </c:if>
      </c:forEach>
   </div>
</c:if>