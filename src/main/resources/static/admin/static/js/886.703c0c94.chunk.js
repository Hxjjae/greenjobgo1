"use strict";(self.webpackChunkgreenjobgo_admin=self.webpackChunkgreenjobgo_admin||[]).push([[886],{9886:(e,n,t)=>{t.r(n),t.d(n,{default:()=>C});var a=t(2791),s=t(3637),i=t(1632),l=t(9806),c=t(3405),r=t(184);const o=e=>{let{page:n,setPage:t,count:a}=e;return(0,r.jsx)(c.G,{children:(0,r.jsx)(s.Z,{activePage:n,itemsCountPerPage:10,totalItemsCount:a,pageRangeDisplayed:10,marginPagesDisplayed:0,prevPageText:(0,r.jsx)(l.G,{icon:i.A35}),nextPageText:(0,r.jsx)(l.G,{icon:i._tD}),firstPageText:"",lastPageText:"",onChange:t})})},d=e=>{let{listData:n,page:t,handleDeleteClick:a}=e;return(0,r.jsxs)("ul",{children:[(0,r.jsx)("li",{className:"bulk-list",children:(0,r.jsxs)("ul",{children:[(0,r.jsx)("li",{className:"bulk-table-th",children:"\ubc88\ud638"}),(0,r.jsx)("li",{className:"bulk-table-th",children:"\ub300\ubd84\ub958"}),(0,r.jsx)("li",{className:"bulk-table-th",children:"\uacfc\uc815\uba85"}),(0,r.jsx)("li",{className:"bulk-table-th",children:"\ud68c\ucc28"}),(0,r.jsx)("li",{className:"bulk-table-th",children:"\uc218\uac15\uae30\uac04"}),(0,r.jsx)("li",{className:"bulk-table-th",children:"\uad00\ub9ac"})]})}),n.length>0&&n.map(((e,n)=>(0,r.jsx)("li",{children:(0,r.jsxs)("ul",{children:[(0,r.jsx)("li",{children:10*(t-1)+n+1}),(0,r.jsx)("li",{children:e.classification}),(0,r.jsx)("li",{children:e.subjectName}),(0,r.jsx)("li",{children:e.round}),(0,r.jsxs)("li",{children:[e.startedAt," ~ ",e.endedAt]}),(0,r.jsx)("li",{children:(0,r.jsx)("button",{onClick:()=>a(e.icourseSubject,e.iclassification),children:"\uc0ad\uc81c"})})]})},e.icourseSubject)))]})},p=e=>{let{category:n,handleCategoryFiiter:t,searchsubj:a,handleSubjectFilter:s,categoryData:i,subjData:l,handleSearch:c}=e;return(0,r.jsxs)("ul",{className:"delete-search",children:[(0,r.jsxs)("li",{className:"select-wrap",children:[(0,r.jsx)("span",{children:"\uc9c1\uc885"}),(0,r.jsxs)("select",{value:n,name:"category-state",onChange:t,children:[(0,r.jsx)("option",{name:"category-state",value:"",children:"\uc120\ud0dd"}),i&&i.map((e=>(0,r.jsx)("option",{name:"category-state",value:e.iclassification,children:e.classification},e.iclassification)))]})]}),(0,r.jsxs)("li",{className:"select-wrap",children:[(0,r.jsx)("span",{children:"\uacfc\uc815\uba85"}),(0,r.jsxs)("select",{name:"subject-state",value:a,onChange:s,children:[(0,r.jsx)("option",{name:"subject-state",value:"",children:"\uacfc\uc815\uba85 \uc120\ud0dd"}),l&&l.map((e=>(0,r.jsx)("option",{value:e.icourseSubject,children:e.subjectName},e.icourseSubject)))]})]}),(0,r.jsx)("li",{children:(0,r.jsx)("button",{onClick:c,children:"\uc870\ud68c"})})]})};var h,u,x,g=t(168),b=t(456),j=t(2291);const y=b.Z.div(h||(h=(0,g.Z)(["\n  width: 100%;\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  flex-direction: column;\n  .delete-title {\n    width: 1350px;\n    display: flex;\n    justify-content: flex-start;\n    align-items: center;\n    gap: 30px;\n    padding: 30px 0 10px 0;\n    h3 {\n      font-size: 24px;\n      padding-left: 10px;\n    }\n    div {\n      img {\n        margin-right: 5px;\n      }\n    }\n  }\n"]))),f=b.Z.div(u||(u=(0,g.Z)(["\n  .delete-search {\n    display: flex;\n    align-items: center;\n    justify-content: center;\n    width: 1350px;\n    height: 100px;\n    border-radius: 5px;\n    background: ",";\n    gap: 30px;\n    margin-bottom: 70px;\n    /* > li { */\n    /* padding-right: 20px; */\n    .select-wrap {\n      /* border-radius: 6px; */\n      display: flex;\n      align-items: center;\n      background: ",";\n      span {\n        display: inline-block;\n        height: 40px;\n        line-height: 2.3;\n        font-size: 16px;\n        font-weight: 500;\n        padding-right: 10px;\n        background: ",";\n      }\n\n      select {\n        width: 155px;\n        height: 40px;\n        -o-appearance: none;\n        -webkit-appearance: none;\n        -moz-appearance: none;\n        appearance: none;\n        border: 1px solid ",";\n        border-radius: 0;\n        background: url(","/assets/Arrowdown.png)\n          no-repeat calc(100% - 12px) 50%/11px auto;\n        padding: 0 28px 0 10px;\n      }\n      select::-ms-expand {\n        display: none;\n      }\n    }\n    li:nth-of-type(1) {\n      > select {\n        width: 160px;\n        height: 40px;\n        cursor: pointer;\n      }\n    }\n    li:nth-of-type(2) {\n      > select {\n        width: 540px;\n        height: 40px;\n        cursor: pointer;\n      }\n    }\n    li:nth-of-type(3) {\n      button {\n        width: 140px;\n        height: 40px;\n        cursor: pointer;\n        border: none;\n        border-radius: 5px;\n        color: #fff;\n        background: ",";\n      }\n    }\n  }\n  /* } */\n  .total-count {\n    padding-left: 10px;\n    font-size: 14px;\n    color: ",";\n    height: 14px;\n    line-height: 14px;\n    margin-bottom: 5px;\n  }\n"])),j.Iy.search,j.Iy.white,j.Iy.search,j.Iy.input,"",j.Iy.btn,j.Iy.btn),m=b.Z.div(x||(x=(0,g.Z)(["\n  position: relative;\n  width: 100%;\n  height: 54vh;\n  text-align: center;\n  /* border: 1px solid ","; */\n  /* border-radius: 5px; */\n  overflow: hidden;\n  background: #fff;\n  /* box-shadow: 0px 0.5px 5px 0px rgba(0, 0, 0, 0.2); */\n  /* box-sizing: border-box; */\n  > ul {\n    width: 1350px;\n    height: 100%;\n    display: grid;\n    grid-template-columns: 1fr;\n    grid-template-rows: repeat(11, 44.6px);\n    border-top: 1px solid ",";\n    > li {\n      line-height: 42px;\n      /* border-top: 1px solid ","; */\n      border: 1px solid ",";\n      border-bottom: none;\n      :first-of-type {\n        background: ",";\n        color: ",";\n        border-top: 0;\n      }\n      :last-of-type {\n        border-bottom: 1px solid ",";\n      }\n      > ul {\n        height: 100%;\n        display: grid;\n        grid-template-columns: 0.15fr 0.4fr 1.3fr 0.2fr 0.55fr 0.3fr;\n        > li {\n          padding: 0 5px;\n          ","\n          :not(:last-of-type) {\n            border-bottom: none;\n            border-right: 1px solid ",";\n          }\n          :last-of-type {\n            > button {\n              width: 65px;\n              height: 32px;\n              color: #fff;\n              background: ",";\n              border: none;\n              border-radius: 4px;\n              cursor: pointer;\n            }\n          }\n        }\n      }\n    }\n  }\n"])),j.Iy.sectiontitle,j.Iy.sectiontitle,j.Iy.sectiontitle,j.Iy.sectiontitle,j.Iy.search,j.Iy.black,j.Iy.sectiontitle,j.LH.one,j.Iy.sectiontitle,j.Iy.input);var k=t(361);var w=t(6064),S=t(3508),v=(t(9190),t(6030),t(3986));const C=()=>{const[e,n]=(0,a.useState)(!1),[t,s]=(0,a.useState)([]),[i,l]=(0,a.useState)([]),[c,h]=(0,a.useState)([]),[u,x]=(0,a.useState)({}),[g,b]=(0,a.useState)(0),[j,C]=(0,a.useState)(1),[D,I]=(0,a.useState)(0),[N,P]=(0,a.useState)(0),[Z,L]=(0,a.useState)(!1),[z,A]=(0,a.useState)(!1),[F,T]=(0,a.useState)(""),[B,G]=(0,a.useState)(!1),R=()=>{(async(e,n,t,a,s,i,l)=>{try{let i="/admin/student/sub-category-list?page=".concat(n,"&size=10&sort=icourseSubject%2CDESC");a&&(i+="&iclassification=".concat(a)),s&&(i+="&icourseSubject=".concat(s));const c=await k.Lp.get(i);e(c.data.subject),t(c.data.page.idx),l(!1),0===c.data.subject.length&&l(!0)}catch(c){i("Bulk Delete List: ".concat(c.message))}})(s,j,I,N,g,T,n)};(0,a.useEffect)((()=>{R(),(async(e,n)=>{try{e((await k.Lp.get("/admin/category")).data)}catch(t){n("Category: ".concat(t.message))}})(l,T)}),[j]);return(0,r.jsxs)(y,{children:[Z&&(0,r.jsx)(w.B6,{modalOpen:Z,setModalOpen:L,handleDelete:async()=>{if(u){const{icourseSubject:n,iclassification:t}=u;try{(async(e,n,t)=>{try{const a=await k.Lp.delete("/admin/student/student-list?iclassification=".concat(n,"&icourseSubject=").concat(e));a.data,console.log("\uc0ad\uc81c\uc131\uacf5",a),200===a.status&&t("\uc0ad\uc81c\uac00 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4.")}catch(a){console.log("\uc0ad\uc81c\uc2e4\ud328",a),t("\uc0ad\uc81c\uac00 \uc815\uc0c1\uc801\uc73c\ub85c \ucc98\ub9ac\ub418\uc9c0 \uc54a\uc558\uc2b5\ub2c8\ub2e4.")}})(n,t,T),A(!0)}catch(e){console.log("error",e)}}}}),z&&(0,r.jsx)(S.Z,{header:"",open:z,close:()=>{A(!1),T("")},onConfirm:()=>{A(!1),T("")},children:(0,r.jsx)("span",{children:F})}),(0,r.jsx)("div",{className:"delete-title",children:(0,r.jsx)("h3",{children:"\uc77c\uad04 \uc0ad\uc81c"})}),(0,r.jsxs)(f,{children:[(0,r.jsx)(p,{category:N,handleCategoryFiiter:async e=>{const n=e.target.value;P(n),await(async(e,n,t)=>{try{const t=await k.Lp.get("/admin/student/dropbox-category?iclassification=".concat(e));console.log(t.data.res),n(t.data.res)}catch(a){t("Bulk Delete Cate List: ".concat(a.message))}})(n,h,T),C(1)},searchsubj:g,setSearchsubj:b,handleSubjectFilter:e=>{b(e.target.value),C(1)},categoryData:i,subjData:c,setCategory:P,handleSearch:()=>{C(1),R()}}),(0,r.jsx)("div",{className:"total-count",children:(0,r.jsxs)("span",{children:["\ucd1d ",D,"\uac1c"]})}),(0,r.jsxs)(m,{children:[e&&(0,r.jsx)(v.Z,{}),(0,r.jsx)(d,{listData:t,page:j,uploadResult:B,setUpLoadResult:G,categoryData:i,handleDeleteClick:(e,n)=>{x({icourseSubject:e,iclassification:n}),L(!0)}})]}),(0,r.jsx)(o,{page:j,setPage:C,count:D})]})]})}}}]);
//# sourceMappingURL=886.703c0c94.chunk.js.map