"use strict";(self.webpackChunkgreenjobgo_admin=self.webpackChunkgreenjobgo_admin||[]).push([[886],{9886:(e,n,t)=>{t.r(n),t.d(n,{default:()=>C});var a=t(2791),s=t(3637),i=t(1632),r=t(9806),c=t(3405),o=t(184);const l=e=>{let{page:n,setPage:t,count:a}=e;return(0,o.jsx)(c.G,{children:(0,o.jsx)(s.Z,{activePage:n,itemsCountPerPage:10,totalItemsCount:a,pageRangeDisplayed:10,marginPagesDisplayed:0,prevPageText:(0,o.jsx)(r.G,{icon:i.A35}),nextPageText:(0,o.jsx)(r.G,{icon:i._tD}),firstPageText:"",lastPageText:"",onChange:t})})},d=e=>{let{listData:n,page:t,handleDeleteClick:a}=e;return(0,o.jsxs)("ul",{children:[(0,o.jsx)("li",{className:"bulk-list",children:(0,o.jsxs)("ul",{children:[(0,o.jsx)("li",{className:"bulk-table-th",children:"\ubc88\ud638"}),(0,o.jsx)("li",{className:"bulk-table-th",children:"\ub300\ubd84\ub958"}),(0,o.jsx)("li",{className:"bulk-table-th",children:"\uacfc\uc815\uba85"}),(0,o.jsx)("li",{className:"bulk-table-th",children:"\ud68c\ucc28"}),(0,o.jsx)("li",{className:"bulk-table-th",children:"\uc218\uac15\uae30\uac04"}),(0,o.jsx)("li",{className:"bulk-table-th",children:"\uad00\ub9ac"})]})}),n.length>0&&n.map(((e,n)=>(0,o.jsx)("li",{children:(0,o.jsxs)("ul",{children:[(0,o.jsx)("li",{children:10*(t-1)+n+1}),(0,o.jsx)("li",{children:e.classification}),(0,o.jsx)("li",{children:e.subjectName}),(0,o.jsx)("li",{children:e.round}),(0,o.jsxs)("li",{children:[e.startedAt," ~ ",e.endedAt]}),(0,o.jsx)("li",{children:(0,o.jsx)("button",{onClick:()=>a(e.icourseSubject,e.iclassification),children:"\uc0ad\uc81c"})})]})},e.icourseSubject)))]})},p=e=>{let{category:n,handleCategoryFiiter:t,searchsubj:a,handleSubjectFilter:s,categoryData:i,subjData:r,handleSearch:c}=e;return(0,o.jsxs)("ul",{className:"delete-search",children:[(0,o.jsxs)("li",{className:"select-wrap",children:[(0,o.jsx)("span",{children:"\uc9c1\uc885"}),(0,o.jsxs)("select",{value:n,name:"category-state",onChange:t,children:[(0,o.jsx)("option",{name:"category-state",value:"",children:"\uc120\ud0dd"}),i&&i.map((e=>(0,o.jsx)("option",{name:"category-state",value:e.iclassification,children:e.classification},e.iclassification)))]})]}),(0,o.jsxs)("li",{className:"select-wrap",children:[(0,o.jsx)("span",{children:"\uacfc\uc815\uba85"}),(0,o.jsxs)("select",{name:"subject-state",value:a,onChange:s,children:[(0,o.jsx)("option",{name:"subject-state",value:"",children:"\uacfc\uc815\uba85 \uc120\ud0dd"}),r&&r.map((e=>(0,o.jsx)("option",{value:e.icourseSubject,children:e.subjectName},e.icourseSubject)))]})]}),(0,o.jsx)("li",{children:(0,o.jsx)("button",{onClick:c,children:"\uc870\ud68c"})})]})};var h,u,x,g=t(168),b=t(456),j=t(2291);const f=b.Z.div(h||(h=(0,g.Z)(["\n  width: 100%;\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  flex-direction: column;\n  .delete-title {\n    width: 1350px;\n    display: flex;\n    justify-content: flex-start;\n    align-items: center;\n    gap: 30px;\n    padding: 30px 0 10px 0;\n    h3 {\n      font-size: 24px;\n      padding-left: 10px;\n    }\n    div {\n      img {\n        margin-right: 5px;\n      }\n    }\n  }\n"]))),y=b.Z.div(u||(u=(0,g.Z)(["\n  .delete-search {\n    display: flex;\n    align-items: center;\n    justify-content: center;\n    width: 1350px;\n    height: 100px;\n    border-radius: 5px;\n    background: ",";\n    gap: 30px;\n    margin-bottom: 70px;\n    /* > li { */\n    /* padding-right: 20px; */\n    .select-wrap {\n      /* border-radius: 6px; */\n      display: flex;\n      align-items: center;\n      background: ",";\n      span {\n        display: inline-block;\n        height: 40px;\n        line-height: 2.3;\n        font-size: 16px;\n        font-weight: 500;\n        padding-right: 10px;\n        background: ",";\n      }\n\n      select {\n        width: 155px;\n        height: 40px;\n        -o-appearance: none;\n        -webkit-appearance: none;\n        -moz-appearance: none;\n        appearance: none;\n        border: 1px solid ",";\n        border-radius: 0;\n        background: url(","/assets/Arrowdown.png)\n          no-repeat calc(100% - 12px) 50%/11px auto;\n        padding: 0 28px 0 10px;\n      }\n      select::-ms-expand {\n        display: none;\n      }\n    }\n    li:nth-of-type(1) {\n      > select {\n        width: 160px;\n        height: 40px;\n        cursor: pointer;\n      }\n    }\n    li:nth-of-type(2) {\n      > select {\n        width: 540px;\n        height: 40px;\n        cursor: pointer;\n      }\n    }\n    li:nth-of-type(3) {\n      button {\n        width: 140px;\n        height: 40px;\n        cursor: pointer;\n        border: none;\n        border-radius: 5px;\n        color: #fff;\n        background: ",";\n      }\n    }\n  }\n  /* } */\n  .total-count {\n    padding-left: 10px;\n    font-size: 14px;\n    color: ",";\n    height: 14px;\n    line-height: 14px;\n    margin-bottom: 5px;\n  }\n"])),j.Iy.search,j.Iy.white,j.Iy.search,j.Iy.input,"",j.Iy.btn,j.Iy.btn),m=b.Z.div(x||(x=(0,g.Z)(["\n  position: relative;\n  width: 100%;\n  height: 54vh;\n  text-align: center;\n  /* border: 1px solid ","; */\n  /* border-radius: 5px; */\n  overflow: hidden;\n  background: #fff;\n  /* box-shadow: 0px 0.5px 5px 0px rgba(0, 0, 0, 0.2); */\n  /* box-sizing: border-box; */\n  > ul {\n    width: 1350px;\n    height: 100%;\n    display: grid;\n    grid-template-columns: 1fr;\n    grid-template-rows: repeat(11, 44.6px);\n    border-top: 1px solid ",";\n    > li {\n      line-height: 42px;\n      /* border-top: 1px solid ","; */\n      border: 1px solid ",";\n      border-bottom: none;\n      :first-of-type {\n        background: ",";\n        color: ",";\n        border-top: 0;\n      }\n      :last-of-type {\n        border-bottom: 1px solid ",";\n      }\n      > ul {\n        height: 100%;\n        display: grid;\n        grid-template-columns: 0.15fr 0.4fr 1.3fr 0.2fr 0.55fr 0.3fr;\n        > li {\n          padding: 0 5px;\n          ","\n          :not(:last-of-type) {\n            border-bottom: none;\n            border-right: 1px solid ",";\n          }\n          :last-of-type {\n            > button {\n              width: 65px;\n              height: 32px;\n              color: #fff;\n              background: ",";\n              border: none;\n              border-radius: 4px;\n              cursor: pointer;\n            }\n          }\n        }\n      }\n    }\n  }\n"])),j.Iy.sectiontitle,j.Iy.sectiontitle,j.Iy.sectiontitle,j.Iy.sectiontitle,j.Iy.search,j.Iy.black,j.Iy.sectiontitle,j.LH.one,j.Iy.sectiontitle,j.Iy.input);var w=t(361);var k=t(6064),S=t(3508),v=(t(7592),t(6030),t(3986));const C=()=>{const[e,n]=(0,a.useState)(!1),[t,s]=(0,a.useState)([]),[i,r]=(0,a.useState)([]),[c,h]=(0,a.useState)([]),[u,x]=(0,a.useState)({}),[g,b]=(0,a.useState)(0),[j,C]=(0,a.useState)(1),[N,I]=(0,a.useState)(0),[D,E]=(0,a.useState)(0),[P,Z]=(0,a.useState)(0),[z,L]=(0,a.useState)(0),[A,F]=(0,a.useState)(!1),[T,G]=(0,a.useState)(!1),[R,_]=(0,a.useState)(""),[O,B]=(0,a.useState)(!1),H=()=>{(async(e,n,t,a,s,i,r)=>{console.log("clickCate",a),console.log("clickSubj",s);try{let i="/admin/student/sub-category-list?page=".concat(n,"&size=10&sort=icourseSubject%2CDESC");a&&(i+="&iclassification=".concat(a)),s&&(i+="&icourseSubject=".concat(s));const c=await w.Lp.get(i);e(c.data.subject),t(c.data.page.idx),r(!1),0===c.data.subject.length&&r(!0)}catch(c){const{response:e}=c,{status:n}=e;if(!e)throw new Error("Network Error");switch(n){case 500:i("[".concat(n,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:i("[".concat(n,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:i("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}})(s,j,I,P,z,_,n)};return(0,a.useEffect)((()=>{H(),(async(e,n)=>{try{e((await w.Lp.get("/admin/category")).data)}catch(t){n("Category: ".concat(t.message))}})(r,_)}),[j,P,z]),(0,o.jsxs)(f,{children:[A&&(0,o.jsx)(k.B6,{modalOpen:A,setModalOpen:F,handleDelete:async()=>{if(u){const{icourseSubject:n,iclassification:t}=u;try{(async(e,n,t)=>{try{const a=await w.Lp.delete("/admin/student/student-list?iclassification=".concat(n,"&icourseSubject=").concat(e));a.data,console.log("\uc0ad\uc81c\uc131\uacf5",a),200===a.status&&t("\uc0ad\uc81c\uac00 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4.")}catch(a){const{response:e}=a,{status:n}=e;if(!e)throw new Error("Network Error");switch(n){case 500:t("[".concat(n,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:t("[".concat(n,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:t("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}})(n,t,_),G(!0)}catch(e){console.log("error",e)}}}}),T&&(0,o.jsx)(S.Z,{header:"",open:T,close:()=>{G(!1),_("")},onConfirm:()=>{G(!1),_("")},children:(0,o.jsx)("span",{children:R})}),(0,o.jsx)("div",{className:"delete-title",children:(0,o.jsx)("h3",{children:"\uc77c\uad04 \uc0ad\uc81c"})}),(0,o.jsxs)(y,{children:[(0,o.jsx)(p,{category:D,handleCategoryFiiter:async e=>{const n=e.target.value;E(n),await(async(e,n,t)=>{try{const t=await w.Lp.get("/admin/student/dropbox-category?iclassification=".concat(e));console.log(t.data.res),n(t.data.res)}catch(a){const{response:e}=a,{status:n}=e;if(!e)throw new Error("Network Error");switch(n){case 500:t("[".concat(n,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:t("[".concat(n,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:t("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}})(n,h,_)},searchsubj:g,setSearchsubj:b,handleSubjectFilter:e=>{b(e.target.value)},categoryData:i,subjData:c,setCategory:E,handleSearch:()=>{Z(D),L(g),C(1)}}),(0,o.jsx)("div",{className:"total-count",children:(0,o.jsxs)("span",{children:["\ucd1d ",N,"\uac1c"]})}),(0,o.jsxs)(m,{children:[e&&(0,o.jsx)(v.Z,{}),(0,o.jsx)(d,{listData:t,page:j,uploadResult:O,setUpLoadResult:B,categoryData:i,handleDeleteClick:(e,n)=>{x({icourseSubject:e,iclassification:n}),F(!0)}})]}),(0,o.jsx)(l,{page:j,setPage:C,count:N})]})]})}}}]);
//# sourceMappingURL=886.2b6feb24.chunk.js.map