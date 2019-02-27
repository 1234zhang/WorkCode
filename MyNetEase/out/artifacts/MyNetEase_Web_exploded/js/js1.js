var repeat = 4



var lunBo_start = setInterval(function(){
    if(repeat == 0){
        repeat = 4
    }
    else{
        $("img.lunbo").attr("src","../img/lunbo"+repeat+".png")
        $(".gundong").css("background",'url("../img/lunbo"+repeat+"_x.png")')
        repeat -- ;
    }
},3000)





$(".lunbo_zuo").click(function(){
    if(repeat <= 1){
        repeat = 4
    }
    else{
    repeat = repeat-1  ;
    $("img.lunbo").attr("src","../img/lunbo"+repeat+".png")
    }
})
$(".lunbo_you").click(function(){
    if(repeat >= 4){
        repeat = 1
    }
    else{
    repeat = repeat+1  ;
    $("img.lunbo").attr("src","../img/lunbo"+repeat+".png")
    }
})

