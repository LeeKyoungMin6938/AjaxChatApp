<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<title>JSP AJAX 실시간 익명 채팅 사이트</title>
</head>
<body>
<!-- https://www.youtube.com/watch?v=_eC_lbmoizg&list=PLRx0vPvlEmdAVcSdYgqjJ64A7ggHhorU_ 나동빈 실시간 ajax 채팅방-->
<!-- div class container 로 최상단 감싸줬던거 지움. (굳이 쓸 필요없는것 같아서)-->
<!-- div class row 는 말 그대로 한줄의 공간을 감쌈. row 안에서 col 로 나누는것. -->	
		
		<div class="container bootstrap snippet">
			<div class="row">
				<div class="portlet portlet-default">
					<!-- 제목 -->
					<div class="portlet-heading">
						<div class="portlet-title">
							<h4>
								<i class="fa fa-circle text-green"></i>실시간 채팅방
							</h4>
						</div>
						<div class="clearfix"></div>
					</div><!-- 제목 끝 -->
					<!-- 채팅화면 -->
					<div class="panel-collapse collapse in" id="chat">
						<!-- 채팅보여지는부분 -->
						<div class="portlet-body chat-widget"
							style="overflow-y: auto; width: auto; height: 300px;"> <!-- overflow-y:auto 는 y축(높이)가 늘어날 수록 자동으로 스크롤생기면서 늘어남. -->
							<!-- 맨 위 시간표시 -->
							<div class="row">
								<div class="col-lg-12">
									<p class="text-center textmuted small">2017년 5월 30일</p>
								</div>
							</div> <!-- 시간 표시 끝 -->
							
							<!-- 한명의 사용자가 작성한 채팅 -->
							<div class="row">
								<div class="col-lg-12">
									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" src="images/ratio.png"> <!-- 프로필 사진. 원래 누르면 프로필로 들어가야하지만 지금은 #. -->
										</a>
										<div class="media-body">
											<h4 class="media-heading">이경민</h4>
											<span class="small pull-right">오전 11:03</span>
										</div>
										<p>안녕</p>
									</div>
								</div>
							</div>
							<!-- row 끝 -->
							<hr />

							<!-- 한명의 사용자가 작성한 채팅 -->
							<div class="row">
								<div class="col-lg-12">
									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" src="images/fluctuation.png">
										</a>
										<div class="media-body">
											<h4 class="media-heading">리경민</h4>
											<span class="small pull-right">오전 11:05</span>
										</div>
										<p>ㅇㅇ ㅎㅇ</p>
									</div>
								</div>
							</div>
							<!-- row 끝 -->
							
						</div><!-- 채팅보여지는부분 끝-->
						
						<!-- 입력공간 -->
						<div class="portlet-footer">
							<!-- 이름 -->
							<div class="row">
								<div class="form-group col-xs-4">
									<input style="height: 40px;" type="text" id="chatName"
										class="form-control" placeholder="이름" maxlength="20" />
								</div>
							</div>
							<!-- 채팅 -->
							<div class="row" style="height:90px;">
								<div class="form-group col-xs-10">
									<textarea style="height:80px;" id="chatContent" class="form-control" placeholder="메시지를 입력하세요" maxlength="100"></textarea>
								</div>
								<div class="form-group col-xs-2">
									<button type="button" class="btn btn-default pull-right" onclick="submitFunction();">전송</button>
									<div class="clearfix"></div>
								</div>
							</div>
						</div><!-- 입력공간 끝 -->
						
					</div><!-- 채팅화면 끝 -->
					
				</div>
			</div>
		</div>
	

</body>
</html>