<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../common/common-meta.jsp" />
<c:import url="../common/common-css.jsp" />
<title>走路记</title>
</head>
<body>
	<!-- Fixed navbar -->
	<c:import url="../common/common-top.jsp" />
	<!-- /.navbar -->

	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="fluid_container">
				<div class="camera_wrap camera_emboss pattern_1" id="camera_wrap_4">
					<div data-thumb="${base}/assets/images/slides/thumbs/slide1.jpg"
						data-src="${base}/assets/images/slides/slide1.jpg"></div>
					<div data-thumb="${base}/assets/images/slides/thumbs/slide2.jpg"
						data-src="${base}/assets/images/slides/slide2.jpg"></div>
					<div data-thumb="${base}/assets/images/slides/thumbs/slide3.jpg"
						data-src="${base}/assets/images/slides/slide3.jpg"></div>
				</div>
				<!-- #camera_wrap_3 -->
			</div>
			<!-- .fluid_container -->
		</div>
	</header>
	<!-- /Header -->
	<section id="search">
		<div class="search-panel">
			<form class="form-inline" role="form">
				<div class="form-group">
					<input type="text" class="form-control" id="city"
						placeholder="城市" autocomplete="off">
				</div>
				<div class="form-group hidden-xs adv">
					<div class="input-group">
						<div class="input-group-addon">价格</div>
						<input class="form-control price" type="text" placeholder="1">
					</div>
				</div>
				<div class="form-group hidden-xs adv">
					<div class="input-group">
						<div class="input-group-addon">至</div>
						<input class="form-control price" type="text" placeholder="100">
					</div>
				</div>
			<!-- 	<div class="form-group hidden-xs adv">
					<div class="checkbox custom-checkbox">
						<label><input type="checkbox"><span
							class="fa fa-check"></span> For Rent</label>
					</div>
				</div> -->
				<!-- <div class="form-group hidden-xs adv">
					<div class="checkbox custom-checkbox">
						<label><input type="checkbox"><span
							class="fa fa-check"></span> For Sale</label>
					</div>
				</div> -->
				<div class="form-group">
					<a href="" class="btn btnsearch">搜索</a>
				</div>
			</form>
		</div>
	</section>
	<section class="secpadding">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="title-box clearfix ">
						<h2 class="title-box_primary">欢迎</h2>
					</div>
					<p>Perspiciatis unde omnis iste natus error sit voluptatem. Cum
						sociis natoque penatibus et magnis dis parturient montes ascetur
						ridiculus musull dui. Lorem ipsumulum aenean noummy endrerit
						mauris. Cum sociis natoque penatibuLorem ipsumulum aenean noummy
						endrerit mauris. Cum sociis natoque penatibus et magnis dis
						parturient montes ascetur ridiculus mus. Null dui. Fusce feugiat
						malesuada odio.</p>
					<p>penatibus et magnis dis parturient montes ascetur ridiculus
						musull dui. Lorem ipsumulum aenean noummy endrerit mauris. Cum
						sociis natoque penatibuLorem ipsumulum aenean noummy endrerit
						mauris. Cum sociis natoque penatibus et magnis dis parturient
						montes ascetur ridiculus mus. Null dui. Fusce feugiat malesuada
						odio.</p>
				</div>
			</div>
		</div>
	</section>
	<section class="news-box secpadding">
		<div class="container">
			<h2>
				<span>最新活动</span>
			</h2>
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
					<div class="newsBox">
						<div class="thumbnail">
							<figure>
								<img src="${base}/assets/images/news1.jpg" alt="">
							</figure>
							<div class="caption maxheight2">
								<div class="box_inner">
									<div class="box">
										<p class="title">
											<strong>Lorem ipsum</strong>
										</p>
										<p>Lorem ipsum dolor sit amet, conc tetu er adipi scing.</p>
									</div>
									<div>
										<a href="#" class="btn-inline">more</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
					<div class="newsBox">
						<div class="thumbnail">
							<figure>
								<img src="${base}/assets/images/news2.jpg" alt="">
							</figure>
							<div class="caption maxheight2">
								<div class="box_inner">
									<div class="box">
										<p class="title">
											<strong>Lorem ipsum</strong>
										</p>
										<p>Lorem ipsum dolor sit amet, conc tetu er adipi scing.</p>
									</div>
									<div>
										<a href="#" class="btn-inline">more</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
					<div class="newsBox">
						<div class="thumbnail">
							<figure>
								<img src="${base}/assets/images/news3.jpg" alt="">
							</figure>
							<div class="caption maxheight2">
								<div class="box_inner">
									<div class="box">
										<p class="title">
											<strong>Lorem ipsum</strong>
										</p>
										<p>Lorem ipsum dolor sit amet, conc tetu er adipi scing.</p>
									</div>
									<div>
										<a href="#" class="btn-inline">more</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
					<div class="newsBox">
						<div class="thumbnail">
							<figure>
								<img src="${base}/assets/images/news4.jpg" alt="">
							</figure>
							<div class="caption maxheight2">
								<div class="box_inner">
									<div class="box">
										<p class="title">
											<strong>Lorem ipsum</strong>
										</p>
										<p>Lorem ipsum dolor sit amet, conc tetu er adipi scing.</p>
									</div>
									<div>
										<a href="#" class="btn-inline">more</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="packages" class="secpadding">
		<div class="container">
			<h2>
				<span>活动展示</span>
			</h2>
			<div class="row">
				<div class="col-md-3 col-sm-6">
					<div class="cuadro_intro_hover " style="background-color: #cccccc;">
						<p style="text-align: center;">
							<img src="${base}/assets/images/pic/pic-1.jpg"
								class="img-responsive" alt="">
						</p>
						<div class="caption">
							<div class="blur"></div>
							<div class="caption-text">
								<h3>Project Name</h3>
								<a class=" btn btn-default" href="#">$4600</i></a>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-3 col-sm-6">
					<div class="cuadro_intro_hover " style="background-color: #cccccc;">
						<p style="text-align: center;">
							<img src="${base}/assets/images/pic/pic-2.jpg"
								class="img-responsive" alt="">
						</p>
						<div class="caption">
							<div class="blur"></div>
							<div class="caption-text">
								<h3>Project Name</h3>
								<a class=" btn btn-default" href="#"> $4600</i></a>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-3 col-sm-6">
					<div class="cuadro_intro_hover " style="background-color: #cccccc;">
						<p style="text-align: center;">
							<img src="${base}/assets/images/pic/pic-3.jpg"
								class="img-responsive" alt="">
						</p>
						<div class="caption">
							<div class="blur"></div>
							<div class="caption-text">
								<h3>Project Name</h3>
								<a class=" btn btn-default" href="#">$4600</i></a>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-3 col-sm-6">
					<div class="cuadro_intro_hover " style="background-color: #cccccc;">
						<p style="text-align: center;">
							<img src="${base}/assets/images/pic/pic-4.jpg"
								class="img-responsive" alt="">
						</p>
						<div class="caption">
							<div class="blur"></div>
							<div class="caption-text">
								<h3>Project Name</h3>
								<a class=" btn btn-default" href="#">$4600</i></a>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="row">
				<div class="col-md-3 col-sm-6">
					<div class="cuadro_intro_hover " style="background-color: #cccccc;">
						<p style="text-align: center;">
							<img src="${base}/assets/images/pic/pic-5.jpg"
								class="img-responsive" alt="">
						</p>
						<div class="caption">
							<div class="blur"></div>
							<div class="caption-text">
								<h3>Project Name</h3>
								<a class=" btn btn-default" href="#">$4600</i></a>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-3  col-sm-6">
					<div class="cuadro_intro_hover " style="background-color: #cccccc;">
						<p style="text-align: center;">
							<img src="${base}/assets/images/pic/pic-6.jpg"
								class="img-responsive" alt="">
						</p>
						<div class="caption">
							<div class="blur"></div>
							<div class="caption-text">
								<h3>Project Name</h3>
								<a class=" btn btn-default" href="#">$4600</i></a>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-3  col-sm-6">
					<div class="cuadro_intro_hover " style="background-color: #cccccc;">
						<p style="text-align: center;">
							<img src="${base}/assets/images/pic/pic-7.jpg"
								class="img-responsive" alt="">
						</p>
						<div class="caption">
							<div class="blur"></div>
							<div class="caption-text">
								<h3>Project Name</h3>
								<a class=" btn btn-default" href="#">$4600</i></a>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-3  col-sm-6">
					<div class="cuadro_intro_hover " style="background-color: #cccccc;">
						<p style="text-align: center;">
							<img src="${base}/assets/images/pic/pic-8.jpg"
								class="img-responsive" alt="">
						</p>
						<div class="caption">
							<div class="blur"></div>
							<div class="caption-text">
								<h3>Project Name</h3>
								<a class=" btn btn-default" href="#">$4600</i></a>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>

	<section class="btm-section secpadding">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="title-box clearfix ">
						<h3 class="title-box_primary">活动预告</h3>
					</div>
					<div class="list styled custom-list">
						<ul>
							<li><a
								title="Snatoque penatibus et magnis dis partu rient montes ascetur ridiculus mus."
								href="#">Singapore Township</a></li>
							<li><a
								title="Fusce feugiat malesuada odio. Morbi nunc odio gravida at cursus nec luctus."
								href="#">Mega luxury Villas</a></li>
							<li><a
								title="Penatibus et magnis dis parturient montes ascetur ridiculus mus."
								href="#">RNT Commercial Shopping Mall</a></li>
							<li><a
								title="Morbi nunc odio gravida at cursus nec luctus a lorem. Maecenas tristique orci."
								href="#">SVN Independent & Duplex Houses</a></li>
							<li><a
								title="Snatoque penatibus et magnis dis partu rient montes ascetur ridiculus mus."
								href="#">World wide IT park</a></li>
							<li><a
								title="Fusce feugiat malesuada odio. Morbi nunc odio gravida at cursus nec luctus."
								href="#">North Arena SNT Township</a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-4">
					<div class="title-box clearfix ">
						<h3 class="title-box_primary">大型活动</h3>
					</div>
					<div class="list styled custom-list">
						<ul>
							<li><a
								title="Snatoque penatibus et magnis dis partu rient montes ascetur ridiculus mus."
								href="#">Singapore Township</a></li>
							<li><a
								title="Fusce feugiat malesuada odio. Morbi nunc odio gravida at cursus nec luctus."
								href="#">Mega luxury Villas</a></li>
							<li><a
								title="Penatibus et magnis dis parturient montes ascetur ridiculus mus."
								href="#">RNT Commercial Shopping Mall</a></li>
							<li><a
								title="Morbi nunc odio gravida at cursus nec luctus a lorem. Maecenas tristique orci."
								href="#">SVN Independent & Duplex Houses</a></li>
							<li><a
								title="Snatoque penatibus et magnis dis partu rient montes ascetur ridiculus mus."
								href="#">World wide IT park</a></li>
							<li><a
								title="Fusce feugiat malesuada odio. Morbi nunc odio gravida at cursus nec luctus."
								href="#">North Arena SNT Township</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="title-box clearfix ">
						<h3 class="title-box_primary">客户列表</h3>
					</div>
					<blockquote class="blockquote-1">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Integer posuere erat a ante. Anim pariatur cliche reprehenderit,
							enim eiusmod high life accusamus terry richardson ad squid</p>
						<small>Someone famous in <cite title="Source Title">Source
								Title</cite></small>
					</blockquote>
				</div>

			</div>
		</div>
	</section>

	<c:import url="../common/common-footer.jsp" />
	<c:import url="../common/common-script.jsp" />
	<script>
		jQuery(function() {

			jQuery('#camera_wrap_4').camera({
				height : '750',
				loader : 'bar',
				pagination : false,
				thumbnails : false,
				hover : false,
				opacityOnGrid : false,
				imagePath : base + 'assets/images/'
			});

		});
	</script>

</body>
</html>
