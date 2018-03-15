function loadMenu(){
   // sds.frame.menu.loadMenu(eval(userMenuList));
    $("#sidebar-menu a[mid='" + $("body").attr("data-menu-id") + "']").addClass("active");
    
}