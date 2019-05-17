// JavaScript Document

//轮播图
$(".banner p:gt(0)").hide();
var slideP=$(".banner p");
var slideSpan=$(".banner div span");
var n=0;
slideSpan.eq(0).css("background","#ff0000");
var c=slideP.length;
autoPlay();
function autoPlay(){
    t=setInterval(function(){
	    n++;
	    if(n>=c)n=0;
	    slideP.eq(n).fadeIn(500).siblings("p").fadeOut(500);
	    slideSpan.eq(n).css("background","#ff0000").siblings("span").css("background","#ffa8a8");
    },3000)
}
slideSpan.each(function(i){
	slideSpan.eq(i).hover(function(){
		clearTimeout(t);
		n=i;
		s=setTimeout(function(){
			slideP.eq(n).fadeIn(500).siblings("p").fadeOut(500);
	    		slideSpan.eq(n).css("background","#ff0000").siblings("span").css("background","#ffa8a8");
		},200);
	},function(){
		clearTimeout(s);
		autoPlay();
	});
});

//左右滑动
      //箭头左右滑动
 var m=0
 $('.product .og_prev ').click(function(){
       m-=850;
	   if(m==-1700) m=0;
	   $('.product .sc ul').animate({marginLeft:m},1000);
 
 })
 
 $('.product .og_next').click(function(){
	    if(m==0) m=-1700;
		m+=850;
		$('.product .sc ul').animate({marginLeft: m},1000) 
 });
          //自动播放
   var flag=0;
  function autoMove(){
	      t2=setInterval(function(){
			    if(flag == 0){
					 $('.product .sc ul').animate({'marginLeft':'-850px'},1000);
						 flag=1; 
						 
				}else if(flag==1){
					$('.product .sc ul').animate({'marginLeft': '0px'},1000) 
					  flag=0;
				}
				
		   },3000);  
  }  
  autoMove();
       //鼠标放上停止播放
  
  $('.product').hover(function(){
         clearInterval(t2);
  },function(){
         autoMove();
  }) 















//导航

//$("#nav_ul >li:has('.icon')").hover(
//	function(){
//    	$('.icon').show();
//    },function(){
//    	$('.icon').hide();
//    });