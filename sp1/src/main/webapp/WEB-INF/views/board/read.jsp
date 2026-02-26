<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>

<div class="row justify-content-center">
  <div class="col-lg-12">
   	<div class="card shadow mb-4">
     	<div class="card-header py-3">
      	<h6 class="m-0 font-weight-bold text-primary">Board Read</h6>
     	</div>
     
     	<div class="card-body">
				<div class="mb-3 input-group input-group-lg">
			    <span class="input-group-text">Bno</span>
			    <input type="text" class="form-control" value="<c:out value="${boardDTO.bno}" />" readonly>
			  </div>
			
			  <div class="mb-3 input-group input-group-lg">
			    <span class="input-group-text">Title</span>
			    <input type="text" name="title" class="form-control" value="<c:out value="${boardDTO.title}" />" readonly>
			  </div>
			
			  <div class="mb-3 input-group input-group-lg">
			    <span class="input-group-text">Content</span>
			    <textarea class="form-control" name="content" rows="3" readonly><c:out value="${boardDTO.content}" /></textarea>
			  </div>
			
			  <div class="mb-3 input-group input-group-lg">
			    <span class="input-group-text">Writer</span>
			    <input type="text" name="writer" class="form-control" value="<c:out value="${boardDTO.writer}" />" readonly>
			  </div>
			
			  <div class="mb-3 input-group input-group-lg">
			    <span class="input-group-text">RegDate</span>
			    <input type="text" name="regDate" class="form-control" value="<c:out value="${boardDTO.createdDate}" />" readonly>
			  </div>
			
			  <div class="float-end">
			  	<a href="/board/list">
			    	<button type="button" class="btn btn-info btnList">LIST</button>
			    </a>
			    
			    <c:if test="${!boardDTO.delFlag}">
			    	<a href="/board/modify/${boardDTO.bno}">
			    		<button type="button" class="btn btn-warning btnModify">MODIFY</button>
			    	</a>
			    </c:if>
			  </div>
			  
			</div>
			
   	</div>
  </div>
</div>

<div class="col-lg-12">
	<div class="card shadow mb-4">
		<div class='m-4'>
						<!--댓글 작성 폼 -->
						<form id="replyForm" class="mt-4">
							<!-- 게시글 번호 hidden 처리 -->
							<input type="hidden" name="bno" value="${boardDTO.bno}" />
							
							<div class="mb-3 input-group input-group-lg">
								<span class="input-group-text">Replyer</span>
								<input type="text" name="replyer" class="form-control" required />
							</div>
			
							<div class="mb-3 input-group">
								<span class="input-group-text">Reply Text</span>
								<textarea name="replytext" class="form-control" rows="3" required></textarea>
							</div>
							
							<div class="text-end">
								<button type="submit" class="btn btn-primary addReplyBtn">Submit Reply</button>
							</div>
						</form>
						<!-- 댓글 작성 폼 끝 -->
			</div>
	</div>
</div>

<div class="col-lg-12">
	<div class="card shadow mb-4">
		<div class='m-4'>
			<!--댓글 목록 -->
			<ul class="list-group replyList">
			</ul>
			<!-- 댓글 목록 -->
			
			<!-- 페이징 -->
			<div aria-label="댓글 페이지 네비게이션" class="mt-4">
				<ul class="pagination justify-content-center">
				</ul>
			</div>
			<!-- 페이징 끝 -->
			
		</div>
	</div>
</div>

<div class="modal fade" id="replyModal" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="replyModalLabel">댓글 수정 / 삭제</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			
			<div class="modal-body">
				<form id="replyModForm">
					<input type="hidden" name="rno" value="33">
					<div class="mb-3">
						<label for="replytext" class="form-label">댓글 내용</label>
						<input type="text" name="replytext" id="replytext" class="form-control" value="Reply Text">
					</div>
				</form>
      </div>
      
      <div class="modal-footer">
				<button type="button" class="btn btn-primary btnReplyMod">수정</button>
				<button type="button" class="btn btn-danger btnReplyDel">삭제</button>
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	const replyForm = document.querySelector('#replyForm');
	
	document.querySelector('.addReplyBtn').addEventListener('click', e => {
		e.preventDefault();
		e.stopPropagation();
		
		const formData = new FormData(replyForm);
		
		axios.post('/replies', formData).then(res => {
			console.log("---------- server response ----------")
			console.log(res);
			replyForm.reset();
			
			getReplies(1, true);
		});
	});
	
	// 댓글 목록 요청
	let currentPage = 1; // 현재 댓글 페이지
	let currentSize = 10; // 페이지당 댓글 개수
	
	const bno = ${boardDTO.bno}
	
	function getReplies(pageNum, goLast){
		//axios.get(`/replies/\${bno}/list` , );
		axios.get('/replies/' + bno + '/list' , {
			params:{
				page: pageNum || currentPage,
				size: currentSize
			}
		}).then(res => {
			const data = res.data;

			// console.log(data);
			// 마지막 댓글 페이지 호출
			// ES6 구조 분해 할당 사용(비구조화 할당)
			const {totalCount, page, size} = data;
	
			if (goLast && totalCount > (page * size)) {
				// 마지막 페이지를 계산
				const lastPage = Math.ceil(totalCount / size);
				
				getReplies(lastPage);
			} else {
				currentPage = page;
				currentSize = size;
				
				printReplies(data);
			}
		});
	}
	
	getReplies(1, true);
	
	// 댓글 출력 
	const replyList = document.querySelector('.replyList');
	
	function printReplies(data) {
		const {replyDTOList, page, size, prev, next, start, end, pageNums} = data;
		
		let liStr = '';
		
		for (const replyDTO of replyDTOList) {
			liStr += `
				<li class="list-group-item" data-rno="\${replyDTO.rno}">
					<div class="d-flex justify-content-between">
						<div>
							<strong>\${replyDTO.rno}</strong> - \${replyDTO.replytext}
						</div>
						<div class="text-muted small">
							\${replyDTO.replyDate}
						</div>
					</div>
					<div class="mt-1 text-secondary small">
						\${replyDTO.replyer}
					</div>
				</li>
			`;
		}
		
		replyList.innerHTML = liStr;
		
		let pagingStr = '';
		
		if (prev) {
			pagingStr += `
				<li class="page-item">
					<a class="page-link" href="\${start -1}" tabindex="-1">이전</a>
				</li>
			`;
		}
		
		for (const i of pageNums) {
			pagingStr += `
				<li class="page-item \${i === page ? 'active' : ''}">
					<a class="page-link" href="\${i}">\${i}</a>
				</li>
			`;
		}
		
		if (next) {
			pagingStr += `
				<li class="page-item">
					<a class="page-link" href="\${end + 1}">다음</a>
				</li>
			`;
		}
		
		document.querySelector(".pagination").innerHTML = pagingStr;
		
		document.querySelector(".pagination").addEventListener("click", e => {
			e.preventDefault();
			e.stopPropagation();

			const target = e.target;
			
			const href = target.getAttribute("href");
			
			if (!href) return;
			
			console.log(href); // 페이지 번호
			
			getReplies(href);
		});
	}
	
	
	const replyModal = new bootstrap.Modal(document.querySelector('#replyModal'));
	const replyModForm = document.querySelector('#replyModForm');
	
	replyList.addEventListener('click', e => {
		const targetLi = e.target.closest("li"); // 가장 가까운 상위 요소(자기 자신도 포함)
		const rno = targetLi.dataset.rno;
		
		// data-: 요소에 데이터를 저장하는 용도(사용자 정의 속성을 만듦)
		// input처럼 value 속성을 지원하는 요소가 아닐 때 유용
		
		// 1. const rno = targetLi.getAttribute("data-rno");
		// 2. const rno = targetLi.dataset.rno;
		
		if(!rno) return;
		
		axios.get('/replies/' + rno).then(res => {
			const targetReply = res.data;
			
			//console.log("targetReply.delFlag : " + targetReply.delFlag);
			
			if (targetReply.delFlag === false) {
				replyModForm.querySelector('input[name="rno"]').value = targetReply.rno;
				replyModForm.querySelector('input[name="replytext"]').value = targetReply.replytext;
				
				replyModal.show();
			} else {
				alert('삭제된 댓글은 조회할 수 없습니다.');
			}
		});	// get 요청을 받은 후 모달창 영역에 값을 출력하기
		
	});
		
	document.querySelector('.btnReplyDel').addEventListener('click', e=>{
		const formData = new FormData(replyModForm);
		const rno = formData.get("rno");
		
		axios.delete('/replies/' + rno).then(res =>{
			const data = res.data;
			
			replyModal.hide();
			
			getReplies(currentPage);
		});
		
	});
	
	document.querySelector('.btnReplyMod').addEventListener('click', e => {
		e.preventDefault();
		e.stopPropagation();
		
		const formData = new FormData(replyModForm);
		const rno = formData.get("rno");
		
		axios.put('/replies/' + rno, formData).then(res => {
			const data = res.data;

			replyModal.hide();
			
			getReplies(currentPage);
		});
	});
	
	
	
</script>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
