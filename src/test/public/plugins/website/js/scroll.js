function ban_scroll(banwrap,banbtn,speed,or){
	var banwrap = $(banwrap);
	var banUl = banwrap.find("ul");
	var banLi = banUl.find("li");
	var len = banLi.length;
	//banwrap.width($(window).width());
	banLi.width(banwrap.width());
	banUl.append(banLi.eq(0).clone())
	banUl.css({
		width:banLi.width()*(len+1)
	})
	var banNow=0;
	var banbtn=$(banbtn);
	var span='';
	for(var i=0; i<len;i++){
		span+="<span></span>"
	}
	banbtn.append(span);
	var banbtnSpan=banbtn.find("span");
	banbtnSpan.eq(0).addClass("cur");
	
	banbtnSpan.click(function(){
		index=$(this).index();
		move(index);
	});
	function move(index){
		if(index>len){
			index=1;
			banUl.css({
				left:0
			})
		}
		if(index<0){
			index=len-1;
			banUl.css({
				left:-banLi.width()*len
			})
		}
		banbtnSpan.removeClass("cur").eq(index).addClass("cur");
		if(index>len-1){
			banbtnSpan.eq(0).addClass("cur")
		};
		banUl.stop().animate({
			left:-banLi.width()*index
		},700)
		banNow=index;
	}
	
	var ban=null;
	function banTimer(){
		ban=setInterval(function(){
			banNow++
		move(banNow)
		},speed)
	}
	banwrap.hover(function(){
		clearInterval(ban)
	},function(){
		banTimer()
	})
	if(or == true){
		banTimer()
	}
}
ban_scroll(".banner","#banbtn",4000,true)
ban_scroll("#youshiwrap","#youshibtn",4000,true)

