function scoreFun(object,opts){
    var defaults={
        fen_d:16,ScoreGrade:10,nameScore:"fenshu",parent:"star_score",attitude:"attitude"}
        options=$.extend({},defaults,opts);
        var countScore=object.find("."+options.nameScore);
        var startParent=object.find("."+options.parent);
        var fen_d=options.fen_d;
        var len=options.ScoreGrade;
        startParent.width(fen_d*len);
        var preA=(5/len);
        for(var i=0;i<len;i++){
            var newSpan=$("<a href='javascript:void(0)'></a>");
            newSpan.css({"left":0,"width":fen_d*(i+1),"z-index":len-i});
            newSpan.appendTo(startParent)
        }
        startParent.find("a").each(function(index){
            $(this).click(function(){
                now_cli=index;show(index,$(this))});
                $(this).mouseenter(function(){
                    show(index,$(this))});
                    $(this).mouseleave(function(){
                        if(now_cli>=0){
                            var scor=preA*(parseInt(now_cli)+1);
                            startParent.find("a").removeClass("clibg");
                            startParent.find("a").eq(now_cli).addClass("clibg");
                            var ww=fen_d*(parseInt(now_cli)+1);
                            startParent.find("a").eq(now_cli).css({"width":ww,"left":"0"});
                            if(countScore){
                                countScore.val(scor)}
                            }else{startParent.find("a").removeClass("clibg");
                            if(countScore){
                                countScore.val("")}}})
                            });
                            };