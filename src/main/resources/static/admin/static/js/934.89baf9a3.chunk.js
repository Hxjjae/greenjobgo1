"use strict";(self.webpackChunkgreenjobgo_admin=self.webpackChunkgreenjobgo_admin||[]).push([[934],{5143:(e,t,n)=>{n.d(t,{IY:()=>c,K2:()=>r,SY:()=>s,eZ:()=>i,t1:()=>o});var a=n(361);const r=async e=>{let{selectCate:t,setSubjectList:n,setErrorApiInfo:r}=e;try{const e=await a.Lp.get("/admin/student/dropbox-category?iclassification=".concat(t)),r=await e.data;return n(r.res),r}catch(s){console.log(s)}},s=async(e,t)=>{console.log("\ud559\uc0dd\uad8c\ud55c\uc815\ubcf4 \ubd88\ub7ec\uc635\ub2c8\ub2e4");try{const t=await a.Lp.get("/admin/student/role-list"),n=await t.data;return e(n.res),n}catch(n){const{response:e}=n,{status:a}=e;if(!e)throw new Error("Network Error");switch(a){case 500:t("[".concat(a,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:t("[".concat(a,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:t("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}},o=async e=>{let{subjectPk:t,startDate:n,endDate:r,setErrorApiInfo:s}=e;try{const e=(await a.Lp.patch("/admin/student/editable-yn?icourseSubject=".concat(t,"&startedAt=").concat(n,"&endedAt=").concat(r))).data;return console.log("\ud559\uc0dd\uad8c\ud55c\uc815\ubcf4 \uc218\uc815 \uc131\uacf5",e),s("\uc218\uac15\uc0dd \uad8c\ud55c\uc774 \uc815\uc0c1\uc801\uc73c\ub85c \ubcc0\uacbd \ub418\uc5c8\uc2b5\ub2c8\ub2e4"),e}catch(o){const{response:e}=o,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:s("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:s("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:s("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}},c=async(e,t)=>{console.log("\uae30\uc5c5\uad8c\ud55c\uc815\ubcf4 \ubd88\ub7ec\uc635\ub2c8\ub2e4");try{const t=await a.Lp.get("/admin/company"),n=await t.data;return e(n),n}catch(n){const{response:e}=n,{status:a}=e;if(!e)throw new Error("Network Error");switch(a){case 500:t("[".concat(a,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:t("[".concat(a,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:t("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}},i=async e=>{let{icompany:t,startDate:n,endDate:r,setErrorApiInfo:s}=e;try{const e=(await a.Lp.patch("/admin/company?icompany=".concat(t,"&startedAt=").concat(n,"&endedAt=").concat(r))).data;return console.log("\uae30\uc5c5\uad8c\ud55c\uc218\uc815 \uc131\uacf5",e),s("\uae30\uc5c5 \uad8c\ud55c\uc774 \uc815\uc0c1\uc801\uc73c\ub85c \ubcc0\uacbd \ub418\uc5c8\uc2b5\ub2c8\ub2e4."),e}catch(o){const{response:e}=o,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:s("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:s("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:s("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}}},2397:(e,t,n)=>{n.d(t,{Yp:()=>l,al:()=>i,fq:()=>r,hU:()=>s,jk:()=>o,qf:()=>c});var a=n(361);const r=async(e,t)=>{console.log("\uce74\ud14c\uc815\ubcf4 \ubd88\ub7ec\uc635\ub2c8\ub2e4");try{const t=await a.Lp.get("/admin/category"),n=await t.data;return e(n),n}catch(n){const{response:e}=n,{status:a}=e;if(!e)throw new Error("Network Error");switch(a){case 500:t("[".concat(a,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:t("[".concat(a,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:t("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}},s=async e=>{let{setStudentPFList:t,page:n,setCount:r,resultUrl:s,setNothing:o,setErrorApiInfo:c}=e;console.log("query\ub4e4\uc5b4\uc624\ub0d0 \u314b ",s);try{const e=await a.Lp.get("/admin/student/portfolio?page=".concat(n,"&size=10&").concat(s)),c=await e.data;return t(c),r(c.page.idx),o(!1),0===c.res.length&&o(!0),c}catch(i){const{response:e}=i,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:c("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:c("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:c("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}},o=async e=>{let{savedItemNum:t,isSaved:n,setErrorInfo:r,setErrorApiInfo:s}=e;try{const e=(await a.Lp.patch("/admin/student/storage?istudent=".concat(t,"&storageYn=").concat(n))).data;return console.log("\ubcf4\uad00\ud568 patch\u314b",e),1===n?r("\ubcf4\uad00\ud568 \uc774\ub3d9\uc774 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4."):0===n&&r("\ubcf4\uad00\ud568 \ucde8\uc18c\uac00 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4."),e}catch(o){const{response:e}=o,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:s("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:s("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:s("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}},c=async e=>{let{setSavedPFList:t,page:n,setCount:r,resultUrl:s,setNothing:o,setErrorApiInfo:c}=e;try{const e=await a.Lp.get("/admin/student/storage?page=".concat(n,"&size=10&sort=istudent%2CASC").concat(s)),c=await e.data;return console.log("\ubcf4\uad00\ud568 \ub9ac\uc2a4\ud2b8",e.config.url),t(c),r(c.page.idx),o(!1),0===c.res.length&&o(!0),c}catch(i){const{response:e}=i,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:c("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:c("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:c("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}},i=async e=>{let{clickItems:t,mainYn:n,setErrorInfo:r}=e;try{const e=t.map((e=>"istudent=".concat(e))).join("&"),s=await a.Lp.patch("/admin/student/main?".concat(e,"&companyMainYn=").concat(n)),o=await s.data;return r("\uba54\uc778 \ud3ec\ud2b8\ud3f4\ub9ac\uc624 \uc124\uc815\uc774 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4."),o}catch(s){console.log(s);const{response:e}=s,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:r("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 455:r("".concat(s.response.data.message));break;case 401:r("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:r("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}},l=async e=>{let{query:t,mainYn:n,setErrorCancelInfo:r}=e;try{const e=await a.Lp.patch("/admin/student/main?".concat(t,"&companyMainYn=").concat(n)),s=await e.data;return console.log("\uba54\uc778\ucde8\uc18cpatch\uc131\uacf5",s.url),r("\uba54\uc778 \ud3ec\ud2b8\ud3f4\ub9ac\uc624 \uc124\uc815\uc774 \ucde8\uc18c\ub418\uc5c8\uc2b5\ub2c8\ub2e4."),s}catch(s){const{response:e}=s,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:r("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 455:r("".concat(s.response.data.message));break;case 401:r("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:r("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}}},8934:(e,t,n)=>{n.r(t),n.d(t,{default:()=>C});var a,r,s,o=n(2791),c=n(168),i=n(456),l=n(2291);const d=i.Z.div(a||(a=(0,c.Z)(["\n  position: relative;\n\n  width: 100%;\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  flex-direction: column;\n  .delete-title {\n    width: 1500px;\n    display: flex;\n    justify-content: flex-start;\n    align-items: center;\n    gap: 30px;\n    padding: 30px 0 10px 0;\n    h3 {\n      font-size: 24px;\n      padding-left: 10px;\n    }\n    div {\n      display: flex;\n      align-items: center;\n      margin-top: 3px;\n      img {\n        width: 19px;\n        height: 19px;\n        margin-right: 5px;\n        margin-top: 2px;\n      }\n      span {\n        line-height: 2;\n      }\n    }\n  }\n"]))),p=i.Z.div(r||(r=(0,c.Z)(["\n  .delete-search {\n    display: flex;\n    align-items: center;\n    justify-content: center;\n    width: 1500px;\n    height: 100px;\n    border-radius: 5px;\n    background: ",";\n    gap: 30px;\n\n    li {\n      /* border-radius: 6px; */\n      display: flex;\n      align-items: center;\n      background: ",";\n      span {\n        display: inline-block;\n        height: 40px;\n        line-height: 2.3;\n        font-size: 16px;\n        font-weight: 500;\n        padding-right: 10px;\n        background: ",";\n      }\n\n      select {\n        /* width: 155px; */\n        height: 40px;\n        -o-appearance: none;\n        -webkit-appearance: none;\n        -moz-appearance: none;\n        appearance: none;\n        border: 1px solid ",";\n\n        background: url(","/assets/Arrowdown.png)\n          no-repeat calc(100% - 12px) 50%/11px auto;\n        padding: 0 28px 0 10px;\n      }\n      select::-ms-expand {\n        display: none;\n      }\n    }\n    li:nth-of-type(1) {\n      select {\n        width: 160px;\n        height: 40px;\n        cursor: pointer;\n      }\n    }\n    li:nth-of-type(2) {\n      select {\n        width: 540px;\n        height: 40px;\n        cursor: pointer;\n      }\n    }\n    li:nth-of-type(3) {\n      .delete-form {\n        display: flex;\n        background: ",";\n\n        input {\n          padding-left: 10px;\n          width: 200px;\n          height: 40px;\n          border: 1px solid ",";\n          border-radius: 3px;\n        }\n        label {\n          font-size: 16px;\n          height: 40px;\n          line-height: 40px;\n          font-weight: 500;\n          padding-right: 10px;\n          background: ",";\n        }\n      }\n    }\n    .search-btn {\n      background: ",";\n      button {\n        width: 140px;\n        height: 40px;\n        cursor: pointer;\n        border: none;\n        border-radius: 5px;\n        color: #fff;\n        background: ",";\n      }\n    }\n  }\n  .delete-buttons {\n    position: relative;\n    display: flex;\n    justify-content: flex-end;\n    align-items: center;\n    padding: 40px 0 0px 0;\n    button {\n      width: 105px;\n      height: 40px;\n      color: #fff;\n      background: ",";\n      border: none;\n      border-radius: 6px;\n      cursor: pointer;\n    }\n  }\n  .total-count {\n    font-size: 14px;\n    color: ",";\n    height: 14px;\n    line-height: 14px;\n    margin-bottom: 10px;\n  }\n"])),l.Iy.search,l.Iy.white,l.Iy.search,l.Iy.input,"",l.Iy.search,l.Iy.input,l.Iy.search,l.Iy.search,l.Iy.btn,l.Iy.input,l.Iy.btn),h=i.Z.div(s||(s=(0,c.Z)(["\n  position: relative;\n  width: 100%;\n  height: 54vh;\n  text-align: center;\n  overflow: hidden;\n  background: #fff;\n  /* box-shadow: 0px 0.5px 5px 0px rgba(0, 0, 0, 0.2); */\n  /* box-sizing: border-box; */\n  .list-border {\n    /* border: 1px solid ","; */\n\n    .class-table-th {\n      color: ",";\n      text-align: center;\n      font-size: 14px;\n      font-weight: 500;\n      line-height: 43px;\n    }\n    > ul {\n      /* width: 1500px; */\n      width: 1500px;\n      height: 100%;\n      display: grid;\n      grid-template-columns: 1fr;\n      grid-template-rows: repeat(11, 43px);\n      border-top: 1px solid ",";\n\n      > li {\n        line-height: 42px;\n        /* height: 44px; */\n        border: 1px solid ",";\n        border-bottom: none;\n\n        :first-of-type {\n          background: ",";\n          color: ",";\n          border-top: 0;\n        }\n        :last-of-type {\n          border-bottom: 1px solid ",";\n        }\n        > ul {\n          height: 100%;\n          display: grid;\n          grid-template-columns: 0.15fr 0.25fr 0.55fr 1.5fr 0.4fr 0.8fr 0.5fr 0.25fr 0.4fr 0.45fr;\n          > li {\n            padding: 0 5px;\n            ","\n            :not(:last-of-type) {\n              border-right: 1px solid ",";\n            }\n          }\n          > li:last-of-type {\n            border-bottom: none;\n            /* border-bottom: 1px solid ","; */\n          }\n        }\n      }\n    }\n  }\n"])),l.Iy.sectiontitle,l.Iy.black,l.Iy.sectiontitle,l.Iy.sectiontitle,l.Iy.search,l.Iy.black,l.Iy.sectiontitle,l.LH.one,l.Iy.sectiontitle,l.Iy.sectiontitle);n(5827);var u=n(3637),g=n(3405),x=n(9806),f=n(1632),m=n(184);const b=e=>{let{page:t,setPage:n,count:a}=e;return(0,m.jsx)(g.G,{style:{marginTop:"20px"},children:(0,m.jsx)(u.Z,{activePage:t,itemsCountPerPage:10,totalItemsCount:a,pageRangeDisplayed:10,marginPagesDisplayed:0,prevPageText:(0,m.jsx)(x.G,{icon:f.A35}),nextPageText:(0,m.jsx)(x.G,{icon:f._tD}),firstPageText:"",lastPageText:"",onChange:n})})},y=e=>{let{listData:t,allClick:n,setAllClick:a,clickItems:r,setClickItems:s,page:c}=e;console.log("clickItems",r);return(0,o.useEffect)((()=>{s([]),a(!1)}),[c]),(0,m.jsx)("div",{className:"list-border",children:(0,m.jsxs)("ul",{children:[(0,m.jsx)("li",{className:"class-list",children:(0,m.jsxs)("ul",{children:[(0,m.jsx)("li",{className:"class-table-th",children:(0,m.jsx)("input",{type:"checkbox",id:"allcheck",onChange:e=>{e.target.checked?(s(t.map((e=>{let{istudent:t}=e;return t}))),a(!0)):(s([]),a(!1))},checked:n})}),(0,m.jsx)("li",{className:"class-table-th",children:"\ubc88\ud638"}),(0,m.jsx)("li",{className:"class-table-th",children:"\ub300\ubd84\ub958"}),(0,m.jsx)("li",{className:"class-table-th",children:"\uacfc\uc815\uba85"}),(0,m.jsx)("li",{className:"class-table-th",children:"\ud68c\ucc28"}),(0,m.jsx)("li",{className:"class-table-th",children:"\uc218\uac15\uae30\uac04"}),(0,m.jsx)("li",{className:"class-table-th",children:"\uc774\ub984"}),(0,m.jsx)("li",{className:"class-table-th",children:"\uc131\ubcc4"}),(0,m.jsx)("li",{className:"class-table-th",children:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624"}),(0,m.jsx)("li",{className:"class-table-th",children:"\ubcf4\uad00\ub41c \ud3ec\ud2b8\ud3f4\ub9ac\uc624"})]})}),t.length>0&&t.map(((e,a)=>(0,m.jsx)("li",{children:(0,m.jsxs)("ul",{children:[(0,m.jsx)("li",{className:"check-box-li",children:(0,m.jsx)("input",{type:"checkbox",id:"check".concat(e.istudent),checked:r.includes(e.istudent),value:e.istudent,onChange:a=>{var o,c;o=a.target.checked,c=e.istudent,s((e=>o?[...e,c]:e.filter((e=>e!==c)))),n&&(document.getElementById("allcheck").checked=r.length===t.length)}})}),(0,m.jsx)("li",{children:10*(c-1)+a+1}),(0,m.jsx)("li",{children:e.classification}),(0,m.jsx)("li",{children:e.subjectName}),(0,m.jsx)("li",{children:e.round&&"".concat(e.round,"\ud68c\ucc28")}),(0,m.jsxs)("li",{children:[e.startedAt," ~ ",e.endedAt]}),(0,m.jsx)("li",{children:e.studentName}),(0,m.jsx)("li",{children:e.gender}),(0,m.jsx)("li",{children:e.portfolio}),(0,m.jsx)("li",{children:0===e.storageYn?"\uc5c6\uc74c":1===e.storageYn?"\uc788\uc74c":null})]})},e.istudent)))]})})};n(5143);var w=n(4261),j=n(2397),k=n(361);var E=n(3508);const I=e=>{let{handleSearch:t,searchname:n,setSearchname:a,searchsubj:r,setSearchsubj:s,setSelectCate:c,selectCate:i,setErrorInfo:l}=e;const[d,p]=(0,o.useState)(!1),[h,u]=(0,o.useState)(""),[g,x]=(0,o.useState)([]),[f,b]=(0,o.useState)([]);return(0,o.useEffect)((()=>{(0,j.fq)(x,u)}),[]),(0,o.useEffect)((()=>{p(!!h)}),[h]),(0,o.useEffect)((()=>{(async e=>{let{selectCate:t,setSubjectList:n,setErrorInfo:a}=e;try{const e=await k.Lp.get("/admin/student/dropbox-category?iclassification=".concat(t)),a=await e.data;return n(a.res),a}catch(r){const{response:e}=r,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:a("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:a("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:a("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}})({selectCate:i,setSubjectList:b,setErrorInfo:l})}),[i]),(0,m.jsxs)("ul",{className:"delete-search",children:[(0,m.jsxs)("li",{children:[(0,m.jsx)("span",{children:"\uc9c1\uc885"}),(0,m.jsxs)("select",{value:i,id:"category-state",onChange:e=>(e=>{console.log("\ud544\ud130\ubcc0\uacbde",e.target.value),c(e.target.value),s(""),a("")})(e),children:[(0,m.jsx)("option",{name:"category-state",value:"",defaultValue:!0,children:"\uc120\ud0dd"}),null===g||void 0===g?void 0:g.map((e=>(0,m.jsx)("option",{value:e.iclassification,children:e.classification},(0,w.Z)())))]})]}),(0,m.jsxs)("li",{children:[(0,m.jsx)("span",{children:"\uacfc\uc815\uba85"}),(0,m.jsxs)("select",{value:r,id:"subject-select-student",onChange:e=>(e=>{console.log("\uacfc\ubaa9\uc120\ud0dde",e.target.value),s(e.target.value)})(e),children:[(0,m.jsx)("option",{value:"",defaultValue:!0,children:"\uacfc\uc815\uba85 \uc120\ud0dd"}),null===f||void 0===f?void 0:f.map((e=>(0,m.jsxs)("option",{value:e.subjectName,children:[null!==e.round&&"(".concat(e.round,"\uae30)"),e.subjectName]},(0,w.Z)())))]})]}),(0,m.jsx)("li",{children:(0,m.jsxs)("div",{className:"delete-form",children:[(0,m.jsx)("label",{htmlFor:"student_name",children:"\uc218\uac15\uc0dd \uc774\ub984"}),(0,m.jsx)("input",{type:"text",id:"student_name",placeholder:"\uc218\uac15\uc0dd \uc774\ub984\uc744 \uac80\uc0c9\ud558\uc138\uc694.",name:"category-state",value:n,onChange:e=>a(e.target.value),onKeyDown:e=>{"Enter"===e.key&&t()}})]})}),(0,m.jsx)("li",{className:"search-btn",children:(0,m.jsx)("button",{onClick:t,children:"\uc870\ud68c"})}),d&&(0,m.jsx)(E.Z,{header:"",open:d,close:()=>{p(!1),u("")},onConfirm:()=>{p(!1),u("")},children:(0,m.jsx)("span",{children:h})})]})};var v=n(3986),N=n(5779);const C=()=>{const[e,t]=(0,o.useState)(!1),[n,a]=(0,o.useState)(""),[r,s]=(0,o.useState)(1),[c,i]=(0,o.useState)(0),[l,u]=(0,o.useState)(""),[g,x]=(0,o.useState)(""),[f,w]=(0,o.useState)(0),[j,C]=(0,o.useState)(""),[S,L]=(0,o.useState)([]),[A,D]=(0,o.useState)(!1),[U,Z]=(0,o.useState)(!1),[P,z]=(0,o.useState)(!1),[Y,_]=(0,o.useState)(""),q=async()=>{const e=(()=>{let e="";return""!==j&&0!==j&&(e+="iclassification=".concat(j,"&")),""!==l&&(e+="subjectName=".concat(l,"&")),""!==g&&(e+="studentName=".concat(g,"&")),e=e?e.slice(0,-1):"",e})();await(async e=>{let{setListData:t,setCount:n,page:a,resultUrl:r,setNothing:s,setErrorInfo:o}=e;try{const e=await k.Lp.get("/admin/student/oneyearago?page=".concat(a,"&size=10&sort=istudent%2CASC&").concat(r)),o=await e.data;return t(o.vo),n(o.page.idx),s(!1),0===o.vo.length&&s(!0),o}catch(c){const{response:e}=c,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:o("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:o("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:o("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}}})({setListData:a,setCount:i,page:r,resultUrl:e,setNothing:t,setErrorInfo:_})};console.log("listData",n);return(0,o.useEffect)((()=>{z(!!Y)}),[Y]),(0,o.useEffect)((()=>{q()}),[r,j]),(0,m.jsxs)(d,{children:[(0,m.jsxs)("div",{className:"delete-title",children:[(0,m.jsx)("h3",{children:"\uc601\uad6c \uc0ad\uc81c"}),(0,m.jsxs)("div",{children:[(0,m.jsx)("img",{src:"".concat("","/assets/bxs_error.png"),alt:"\uacbd\uace0"}),(0,m.jsx)("span",{children:"\uc601\uad6c \uc0ad\uc81c \uc2dc \ubcf4\uad00\ud568\uc5d0 \uc800\uc7a5\ub41c \ud3ec\ud2b8\ud3f4\ub9ac\uc624\ub3c4 \ud568\uaed8 \uc0ad\uc81c \ub429\ub2c8\ub2e4."})]})]}),(0,m.jsxs)(p,{children:[(0,m.jsx)(I,{handleSearch:async()=>{C(f),s(1)},searchname:g,setSearchname:x,searchsubj:l,setSearchsubj:u,selectCate:f,setSelectCate:w,setErrorInfo:_}),(0,m.jsx)("div",{className:"delete-buttons",children:(0,m.jsx)("button",{onClick:()=>{0!==S.length?Z(!0):_("\uc0ad\uc81c\ud560 \ub0b4\uc6a9\uc744 \uc120\ud0dd\ud558\uc138\uc694.")},children:"\uc0ad\uc81c"})}),(0,m.jsx)("div",{className:"total-count",children:(0,m.jsxs)("span",{children:["\ucd1d ",c,"\uac1c"]})}),(0,m.jsxs)(h,{children:[e&&(0,m.jsx)(v.Z,{}),(0,m.jsx)(y,{listData:n,page:r,allClick:A,setAllClick:D,clickItems:S,setClickItems:L})]}),(0,m.jsx)(b,{page:r,setPage:s,count:c})]}),U&&(0,m.jsxs)(N.Z,{header:(0,m.jsx)("p",{style:{width:"200px",position:"absolute",top:"20px",left:"25px"},children:"\ud56d\ubaa9\uc744 \uc0ad\uc81c\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?"}),open:U,close:()=>Z(!1),onConfirm:async()=>{try{await(async e=>{let{clickItems:t,setErrorInfo:n,setClickItems:a}=e;try{const e=t.map((e=>"istudent=".concat(e))).join("&"),a=(await k.Lp.delete("/admin/student/oneyearago?".concat(e))).data;return console.log("\uc644\uc804 \uc0ad\uc81c\uc131\uacf5",a),n("\uc601\uad6c \uc0ad\uc81c\uac00 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4."),a}catch(r){const{response:e}=r,{status:t}=e;if(!e)throw new Error("Network Error");switch(t){case 500:n("[".concat(t,"Error] \uc11c\ubc84 \ub0b4\ubd80 \uc624\ub958"));break;case 401:n("[".concat(t,"Error] \ub85c\uadf8\uc778 \uc2dc\uac04\uc774 \ub9cc\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \ub85c\uadf8\uc544\uc6c3 \ud6c4 \uc7ac\uc811\uc18d \ud574\uc8fc\uc138\uc694."));break;default:n("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}return}})({clickItems:S,setErrorInfo:_}),await Z(!1),z(!0)}catch(e){console.log("\uc0ad\uc81c\uc2e4\ud328",e),z(!0)}},onCancel:()=>Z(!1),children:[(0,m.jsx)("img",{src:"".concat("","/assets/bxs_error.png"),alt:"\uacbd\uace0"}),(0,m.jsx)("span",{style:{fontSize:"12px",lineHeight:"2.5"},children:"\ud655\uc778 \ubc84\ud2bc \ud074\ub9ad \uc2dc \ud574\ub2f9 \ud56d\ubaa9\uacfc \ud3ec\ud2b8\ud3f4\ub9ac\uc624\uac00 \uc601\uad6c\uc801\uc73c\ub85c \uc0ad\uc81c\ub429\ub2c8\ub2e4."})]}),P&&(0,m.jsx)(E.Z,{header:"",open:P,close:()=>{z(!1),q()},onConfirm:()=>{z(!1),q()},children:(0,m.jsx)("span",{children:Y})})]})}},4261:(e,t,n)=>{n.d(t,{Z:()=>l});const a={randomUUID:"undefined"!==typeof crypto&&crypto.randomUUID&&crypto.randomUUID.bind(crypto)};let r;const s=new Uint8Array(16);function o(){if(!r&&(r="undefined"!==typeof crypto&&crypto.getRandomValues&&crypto.getRandomValues.bind(crypto),!r))throw new Error("crypto.getRandomValues() not supported. See https://github.com/uuidjs/uuid#getrandomvalues-not-supported");return r(s)}const c=[];for(let d=0;d<256;++d)c.push((d+256).toString(16).slice(1));function i(e){let t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:0;return c[e[t+0]]+c[e[t+1]]+c[e[t+2]]+c[e[t+3]]+"-"+c[e[t+4]]+c[e[t+5]]+"-"+c[e[t+6]]+c[e[t+7]]+"-"+c[e[t+8]]+c[e[t+9]]+"-"+c[e[t+10]]+c[e[t+11]]+c[e[t+12]]+c[e[t+13]]+c[e[t+14]]+c[e[t+15]]}const l=function(e,t,n){if(a.randomUUID&&!t&&!e)return a.randomUUID();const r=(e=e||{}).random||(e.rng||o)();if(r[6]=15&r[6]|64,r[8]=63&r[8]|128,t){n=n||0;for(let e=0;e<16;++e)t[n+e]=r[e];return t}return i(r)}}}]);
//# sourceMappingURL=934.89baf9a3.chunk.js.map