"use strict";(self.webpackChunkgreenjobgo=self.webpackChunkgreenjobgo||[]).push([[978],{3965:(n,e,t)=>{t.d(e,{L2:()=>l,XB:()=>a,ZP:()=>p});var i=t(5294),s=t(2707);const o=i.Z.create({baseURL:"/api",headers:{"Content-Type":"application/json"}}),r=()=>{const n=(0,s.ej)("accessToken");n?o.defaults.headers.common.Authorization="Bearer ".concat(n):delete o.defaults.headers.common.Authorization};o.interceptors.request.use((async n=>(r(),n)),(n=>Promise.reject(n))),o.interceptors.response.use((n=>n),(async n=>{const e=n.config,t=(0,s.ej)("refreshToken");if(401===n.response.status&&t)try{const n=(await o.post("/sign/refresh-token",{refreshToken:t})).data;return(0,s.d8)("accessToken",n),r(),o(e)}catch(n){return console.error("\ud1a0\ud070 \uac31\uc2e0 \uc2e4\ud328:",n),c(),Promise.reject(n)}return console.error("\uc694\uccad \uc2e4\ud328:",n),Promise.reject(n)}));const a=async(n,e,t)=>{try{const i=await o.post("/sign/sign-in",{email:n,pw:e}),{role:a,refreshToken:c,accessToken:l,vo:d,accessTokenTime:p}=await i.data;if(a&&c&&l)return(0,s.d8)("refreshToken",c),(0,s.d8)("accessToken",l),r(),t(""),{role:a,accessToken:l,refreshToken:c,vo:d,accessTokenTime:p};throw new Error("\uc798\ubabb\ub41c \uc751\ub2f5 \ud615\uc2dd")}catch(i){d(i,t)}},c=()=>{(0,s.nJ)("accessToken"),(0,s.nJ)("refreshToken"),delete o.defaults.headers.common.Authorization},l=async()=>{try{await o.post("/sign/logout"),c()}catch(n){console.error("\ub85c\uadf8\uc544\uc6c3 \uc2e4\ud328:",n)}},d=(n,e)=>{throw 432===n.response.status?e("\uc544\uc774\ub514\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"):434===n.response.status?e("\ube44\ubc00\ubc88\ud638\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"):435===n.response.status?e("\uad8c\ud55c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4. \ub2f4\ub2f9\uc790\uc5d0\uac8c \ubb38\uc758 \ubc14\ub78d\ub2c8\ub2e4."):500===n.response.status&&e("\uc11c\ubc84 \uc624\ub958 \uc785\ub2c8\ub2e4."),new Error("\ub85c\uadf8\uc778\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")},p=o},5779:(n,e,t)=>{t.d(e,{Z:()=>o});t(2791);var i=t(2291),s=t(184);const o=n=>{let{header:e,open:t,close:o,onConfirm:r,onCancel:a,children:c}=n;return(0,s.jsx)(i.R9,{onClick:o,children:(0,s.jsx)("div",{className:t?"openConfirmModal Confimmodal":"Confirmmodal",children:t&&(0,s.jsxs)("div",{className:"modalConfirm-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:[(0,s.jsxs)("div",{className:"modal-header",children:[e,(0,s.jsx)("p",{className:"close",onClick:o})]}),(0,s.jsxs)("div",{className:"modalConfirm-content",children:[c,(0,s.jsxs)("div",{children:[(0,s.jsx)(i.MV,{onClick:a,children:"\ucde8\uc18c"}),(0,s.jsx)(i.Us,{onClick:r,children:"\ud655\uc778"})]})]})]})})})}},1385:(n,e,t)=>{t.d(e,{Z:()=>r});t(2791);var i=t(2752),s=t(2291),o=t(184);const r=n=>{let{close:e,open:t,onConfirm:r,onCancel:a,children:c}=n;return(0,o.jsx)(i.m,{onClick:e,children:(0,o.jsx)("div",{className:t?"openModal modal":"modal",children:t?(0,o.jsx)("div",{className:"modal-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:(0,o.jsxs)("div",{className:"modal-content",children:[c,(0,o.jsxs)("div",{className:"btns",children:[(0,o.jsx)(s.MV,{onClick:a,children:"\ucde8\uc18c"}),(0,o.jsx)(s.Us,{onClick:r,children:"\ud655\uc778"})]})]})}):null})})}},3508:(n,e,t)=>{t.d(e,{Z:()=>o});t(2791);var i=t(2291),s=t(184);const o=n=>{let{header:e,open:t,close:o,onConfirm:r,children:a}=n;console.log("\uc624\ucf00\uc774 \ubaa8\ub2ec \uc791\ub3d9\ub429\ub2c8\ub2e4");return(0,s.jsx)(i.R9,{onClick:o,children:(0,s.jsx)("div",{className:t?"openConfirmModal Confimmodal":"Confirmmodal",children:t?(0,s.jsxs)("div",{className:"modalConfirm-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:[(0,s.jsxs)("div",{className:"modal-header",children:[e,(0,s.jsx)("p",{className:"close",onClick:o,children:(0,s.jsx)("img",{src:"".concat("","/assets/btn_menu_close.png"),alt:"X"})})]}),(0,s.jsxs)("div",{className:"modalConfirm-content",children:[a,(0,s.jsx)("div",{children:(0,s.jsx)(i.Us,{onClick:r,children:"\ud655\uc778"})})]})]}):null})})}},1978:(n,e,t)=>{t.r(e),t.d(e,{AgreeStudentModalAtom:()=>v,default:()=>k});var i=t(2791),s=t(7689),o=t(2645),r=t(1087),a=t(3965),c=t(6030),l=t(4151),d=t(6850),p=t(9806),x=t(3508),h=t(5779),m=t(184);const g=()=>{const[n,e]=(0,c.FV)(l.q),[t,g]=(0,i.useState)("myportfolio"),j=(0,s.s0)(),f=(0,c.rb)(v),[u,w]=(0,i.useState)(!1),[y,b]=(0,i.useState)(""),[k,C]=(0,i.useState)(!1),N=[{ibt:"a1",type:"myportfolio",title:"\ub098\uc758 \ud3ec\ud2b8\ud3f4\ub9ac\uc624 \uad00\ub9ac",icon:(0,m.jsx)(p.G,{icon:d.nfZ})},{ibt:"a2",type:"mypage",title:"\ub9c8\uc774\ud398\uc774\uc9c0",icon:(0,m.jsx)(p.G,{icon:d.ILF})},{ibt:"a3",type:"connectcompany",title:"\ud611\uc57d \uae30\uc5c5",icon:(0,m.jsx)(p.G,{icon:d.P88})}];return(0,i.useEffect)((()=>{w(!!y)}),[y]),(0,m.jsxs)(m.Fragment,{children:[(0,m.jsx)(o.w,{children:(0,m.jsxs)("div",{className:"student-header",children:[(0,m.jsx)("div",{className:"upper-logo-div",onClick:()=>{g("myportfolio")},children:(0,m.jsx)(r.rU,{to:"/student",children:(0,m.jsx)("img",{src:"".concat("","/assets/LoginTitle.png"),alt:"greenlogo"})})}),(0,m.jsx)("ul",{className:"header-menu",children:N.map((n=>(0,m.jsx)("li",{onClick:()=>{return e=n.type,void g(e);var e},className:"".concat(t===n.type?"select":""),children:(0,m.jsxs)(r.rU,{to:"./".concat(n.type),children:[n.icon," ",n.title]})},n.ibt)))}),(0,m.jsxs)("div",{className:"loguout-btn",onClick:()=>{C(!0)},children:["\ub85c\uadf8\uc544\uc6c3",(0,m.jsx)("img",{src:"".concat("","/assets/LogoutIcon.svg"),alt:"logout"})]})]})}),u&&(0,m.jsx)(x.Z,{header:"",open:u,close:()=>{w(!1),b("")},onConfirm:()=>{w(!1),b("")},children:(0,m.jsx)("span",{children:y})}),k&&(0,m.jsx)(h.Z,{open:k,close:()=>{C(!1)},onConfirm:()=>{try{f(),(0,a.L2)(),e((n=>({...n,isLogin:!1,accessToken:null,role:"",id:""}))),j("/")}catch(n){b("\ub85c\uadf8\uc544\uc6c3\uc774 \uc815\uc0c1\uc801\uc73c\ub85c \ucc98\ub9ac\ub418\uc9c0 \uc54a\uc558\uc2b5\ub2c8\ub2e4.")}},onCancel:()=>{C(!1)},children:(0,m.jsx)("span",{children:"\ub85c\uadf8\uc544\uc6c3 \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?"})})]})};var j=t(496),f=t(1385),u=t(2752);const w=()=>(0,m.jsxs)(u.v,{children:[" ",(0,m.jsxs)("div",{className:"protect-wrap",children:[(0,m.jsx)("img",{src:"".concat("","/assets/LoginTitle.png"),alt:"greenlogo"}),(0,m.jsxs)("div",{className:"protect-title",children:[(0,m.jsx)("img",{src:"".concat("","/assets/checkIcon.png"),alt:"check"}),(0,m.jsx)("span",{children:" \uac1c\uc778\uc815\ubcf4 \uc81c\uacf5 \ub3d9\uc758 \uc57d\uad00 "})]}),(0,m.jsxs)("div",{className:"content",children:[(0,m.jsx)("span",{children:"1.\uac1c\uc778\uc815\ubcf4\uc758 \uc218\uc9d1 \ubc0f \uc774\uc6a9 \ubaa9\uc801"}),(0,m.jsx)("p",{children:"1.1 \ubcf8\uc778\uc740 \uac1c\uc778\uc815\ubcf4\ub97c \uc81c\uacf5\ud568\uc5d0 \uc788\uc5b4, \uc774\ub825\uc11c \ubc0f \uc790\uae30\uc18c\uac1c\uc11c, \ud6c8\ub828\uacb0\uacfc\ubb3c \uac8c\uc2dc \ubc0f \uc5f4\ub78c\uc744 \uc704\ud55c \uc218\uc9d1 \ubc0f \uc774\uc6a9\uc5d0 \ub3d9\uc758\ud569\ub2c8\ub2e4."}),(0,m.jsx)("br",{}),(0,m.jsx)("span",{children:"2.\uc218\uc9d1\ud558\ub294 \uac1c\uc778\uc815\ubcf4\uc758 \ud56d\ubaa9"}),(0,m.jsx)("p",{children:"2.1 \ubcf8 \ub3d9\uc758 \uc57d\uad00\uc5d0 \ub530\ub77c \uc218\uc9d1\ub418\ub294 \uac1c\uc778\uc815\ubcf4\uc758 \ud56d\ubaa9\uc740 \ub2e4\uc74c\uacfc \uac19\uc2b5\ub2c8\ub2e4."}),(0,m.jsxs)("p",{children:["1)\uc131\uba85 ",(0,m.jsx)("br",{}),"2)\uc5f0\ub77d\ucc98 (\uc774\uba54\uc77c \uc8fc\uc18c, \uc804\ud654\ubc88\ud638 \ub4f1) ",(0,m.jsx)("br",{}),"3)\uc8fc\uc18c, \uad50\uc721 \ubc0f \uacbd\ub825\uc0ac\ud56d ",(0,m.jsx)("br",{}),"4)\uc774\ub825\uc11c \ubc0f \uc790\uae30\uc18c\uac1c\uc11c ",(0,m.jsx)("br",{}),"5)\ud6c8\ub828\uacb0\uacfc\ubb3c \uad00\ub828\ub41c \ud544\uc694\ud55c \uc815\ubcf4"]}),(0,m.jsx)("br",{}),(0,m.jsx)("span",{children:"3.\uac1c\uc778\uc815\ubcf4\uc758 \ubcf4\uc720 \ubc0f \uc774\uc6a9 \uae30\uac04"}),(0,m.jsx)("p",{children:"3.1 \uac1c\uc778\uc815\ubcf4\ub294 \uc218\uc9d1 \ubaa9\uc801 \ub2ec\uc131 \ud6c4 \uc989\uc2dc \ud30c\uae30\ub429\ub2c8\ub2e4."}),(0,m.jsx)("p",{children:"3.2 \ub2e4\ub9cc, \uad00\ub828 \ubc95\ub839\uc5d0 \ub530\ub77c \ubcf4\uc874\uc774 \ud544\uc694\ud55c \uacbd\uc6b0\uc5d0\ub294 \ud574\ub2f9 \ubc95\ub839\uc5d0\uc11c \uc815\ud55c \uae30\uac04 \ub3d9\uc548 \ubcf4\uad00\ub429\ub2c8\ub2e4."})," ",(0,m.jsx)("br",{}),(0,m.jsx)("span",{children:"4.\uac1c\uc778\uc815\ubcf4\uc758 \uc81c\uacf5 \ubc0f \uc81c3\uc790 \uc81c\uacf5\uc5d0 \ub300\ud55c \ub3d9\uc758"}),(0,m.jsx)("p",{children:"4.1 \ubcf8\uc778\uc740 \uc218\uc9d1\ub41c \uac1c\uc778\uc815\ubcf4\uac00 \ud6c8\ub828\uacb0\uacfc\ubb3c \uac8c\uc2dc \ubc0f \uc5f4\ub78c\uc744 \uc704\ud574 \ud544\uc694\ud55c \uacbd\uc6b0 \ud574\ub2f9 \uc815\ubcf4\ub97c \uc81c\uacf5\ud558\ub294 \ub370 \ub3d9\uc758\ud569\ub2c8\ub2e4."}),(0,m.jsx)("p",{children:"4.2 \uac1c\uc778\uc815\ubcf4\ub294 \ub2f9\uc0ac\uc758 \uc11c\ube44\uc2a4\ub97c \uc81c\uacf5\ud558\ub294 \ubaa9\uc801 \uc774\uc678\uc5d0\ub294 \uc5b4\ub5a0\ud55c \uacbd\uc6b0\uc5d0\ub3c4 \uc81c3\uc790\uc5d0\uac8c \uc81c\uacf5\ub418\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4."}),(0,m.jsx)("br",{}),(0,m.jsx)("span",{children:"5.\uac1c\uc778\uc815\ubcf4\uc758 \uc5f4\ub78c, \uc815\uc815, \uc0ad\uc81c\uc5d0 \ub300\ud55c \uad8c\ub9ac"}),(0,m.jsx)("p",{children:"5.1 \ubcf8\uc778\uc740 \uc5b8\uc81c\ub4e0\uc9c0 \uc81c\uacf5\ud55c \uac1c\uc778\uc815\ubcf4\ub97c \uc5f4\ub78c, \uc815\uc815, \uc0ad\uc81c\ud560 \uc218 \uc788\ub294 \uad8c\ub9ac\ub97c \uac00\uc9c0\uace0 \uc788\uc2b5\ub2c8\ub2e4."}),(0,m.jsx)("p",{children:"5.2 \uac1c\uc778\uc815\ubcf4\uc758 \uc5f4\ub78c, \uc815\uc815, \uc0ad\uc81c\ub97c \uc704\ud574\uc11c\ub294 \ub2f9\uc0ac\uc5d0\uac8c \uc11c\uba74 \ub610\ub294 \uc804\uc790\uc6b0\ud3b8\uc73c\ub85c \uc694\uccad\ud558\uc5ec\uc57c \ud569\ub2c8\ub2e4."}),(0,m.jsx)("br",{}),(0,m.jsx)("span",{children:"6.\uac1c\uc778\uc815\ubcf4\uc758 \ud30c\uae30 \uc808\ucc28 \ubc0f \ubc29\ubc95"}),(0,m.jsx)("p",{children:"6.1 \uac1c\uc778\uc815\ubcf4\uc758 \uc218\uc9d1 \ubc0f \uc774\uc6a9 \ubaa9\uc801\uc774 \ub2ec\uc131\ub41c \uacbd\uc6b0 \ud30c\uae30\ub429\ub2c8\ub2e4."})," ",(0,m.jsx)("p",{children:"6.2 \ud30c\uae30\ub41c \uac1c\uc778\uc815\ubcf4\ub294 \uae30\ub85d\ubb3c\uacfc \uc804\uc790\uc801 \ud30c\uc77c \ud615\ud0dc\ub85c \ubaa8\ub450 \uc548\uc804\ud558\uac8c \ud30c\uae30\ub429\ub2c8\ub2e4."}),(0,m.jsx)("br",{}),(0,m.jsx)("span",{children:"7.\uac1c\uc778\uc815\ubcf4\uc758 \uc548\uc804\uc131 \ud655\ubcf4 \uc870\uce58"}),(0,m.jsx)("p",{children:"7.1 \ub2f9\uc0ac\ub294 \uac1c\uc778\uc815\ubcf4\ub97c \uc548\uc804\ud558\uac8c \uad00\ub9ac\ud558\uae30 \uc704\ud558\uc5ec \ub2e4\uc74c\uacfc \uac19\uc740 \uc870\uce58\ub97c \ucde8\ud569\ub2c8\ub2e4."}),(0,m.jsxs)("p",{children:["1)\uac1c\uc778\uc815\ubcf4 \uc554\ud638\ud654 ",(0,m.jsx)("br",{}),"2)\uc811\uadfc \uad8c\ud55c \uc81c\ud55c ",(0,m.jsx)("br",{}),"3)\ubcf4\uc548 \ud504\ub85c\uadf8\ub7a8 \uc124\uce58 \ubc0f \uc8fc\uae30\uc801\uc778 \uac31\uc2e0"]}),(0,m.jsx)("br",{}),(0,m.jsx)("span",{children:"8.\uae30\ud0c0"}),(0,m.jsx)("p",{children:"8.1 \ubcf8 \ub3d9\uc758 \uc57d\uad00\uc740 \uad00\ub828 \ubc95\ub839\uc774\ub098 \uc815\ubd80\uc758 \uc815\ucc45 \ubcc0\uacbd\uc5d0 \ub530\ub77c \ub0b4\uc6a9\uc774 \ubcc0\uacbd\ub420 \uc218 \uc788\uc2b5\ub2c8\ub2e4."}),(0,m.jsx)("p",{children:"8.2 \ubcf8\uc778\uc740 \ubcf8 \ub3d9\uc758 \uc57d\uad00\uc758 \ub0b4\uc6a9\uc744 \uc219\uc9c0\ud558\uace0 \uc5b8\uc81c\ub4e0\uc9c0 \ubcc0\uacbd\ub41c \ub0b4\uc6a9\uc5d0 \ub3d9\uc758\ub97c \uac70\ubd80\ud560 \uc218 \uc788\uc2b5\ub2c8\ub2e4."}),(0,m.jsx)("br",{})]}),(0,m.jsx)("div",{className:"footer-text",children:"\ubcf8\uc778\uc740 \ubcf8 \ub3d9\uc758 \uc57d\uad00\uc758 \ub0b4\uc6a9\uc744 \uc219\uc9c0\ud558\uc600\uc73c\uba70, \ub3d9\uc758\ud569\ub2c8\ub2e4."})]})]});var y=t(9386);const{persistAtom:b}=(0,y.J)(),v=(0,c.cn)({key:"AgreeStudentModalAtom",default:{isStdAgree:!1},effects_UNSTABLE:[b]}),k=()=>{const[n,e]=(0,i.useState)(!0),[t,o]=(0,i.useState)(!1),[r,l]=(0,c.FV)(v),d=(0,s.s0)(),p=async()=>{o(!0)};return(0,m.jsxs)("div",{children:[(0,m.jsx)(g,{}),(0,m.jsx)(j.Xs,{children:(0,m.jsx)(s.j3,{})}),!r.isStdAgree&&(0,m.jsx)(f.Z,{close:p,open:n,onConfirm:()=>{l({isStdAgree:!0}),e(!1)},onCancel:p,children:(0,m.jsx)(w,{})}),t&&(0,m.jsx)(h.Z,{header:"",open:t,close:()=>{o(!1)},onConfirm:async()=>{o(!1),l({isStdAgree:!1});try{await(0,a.L2)(),d("/")}catch(n){console.error("Error during logout:",n)}},onCancel:()=>{o(!1)},children:(0,m.jsx)("span",{children:"\ub3d9\uc758\ub97c \ud558\uc9c0 \uc54a\ub294 \uacbd\uc6b0 \ud574\ub2f9 \uc0ac\uc774\ud2b8\ub97c \uc774\uc6a9\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4."})})]})}},2645:(n,e,t)=>{t.d(e,{w:()=>a});var i,s=t(168),o=t(7686),r=t(2291);const a=o.Z.div(i||(i=(0,s.Z)(["\n  position: fixed;\n  top: 0;\n  left: 0;\n  z-index: 999;\n  width: 100vw;\n  /* max-width: 196rem; */\n  height: 100px;\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  background: ",";\n  box-shadow: 0px 4px 10px 2px rgba(0, 0, 0, 0.12);\n  & > div {\n    width: 1400px;\n    display: flex;\n    justify-content: space-between;\n    align-items: center;\n    cursor: pointer;\n    /* \uc0c1\ub2e8\ub85c\uace0style */\n    .upper-logo-div {\n      width: 256px;\n      height: 20px;\n      img {\n        width: 100%;\n        object-fit: contain;\n      }\n    }\n    .header-menu {\n      display: flex;\n      justify-content: center;\n      align-items: center;\n      gap: 90px;\n      li {\n        > a {\n          position: relative;\n          font-size: 16px;\n          font-weight: 500;\n          color: ",";\n          > svg {\n            position: absolute;\n            top: 3px;\n            left: -25px;\n            width: 17px;\n            height: 17px;\n            filter: invert(48%) sepia(6%) saturate(7%) hue-rotate(323deg)\n              brightness(98%) contrast(80%);\n          }\n        }\n      }\n      .select {\n        > a {\n          color: ",";\n          > svg {\n            filter: invert(6%) sepia(79%) saturate(16%) hue-rotate(349deg)\n              brightness(91%) contrast(85%);\n          }\n        }\n      }\n    }\n    .loguout-btn {\n      font-size: 15px;\n      font-weight: 500;\n      > img {\n        width: 17px;\n        height: 17px;\n        margin-left: 8px;\n      }\n    }\n  }\n  /* \ubc18\uc751\ud615 \ud5e4\ub354 */\n  @media all and (max-width: 840px) {\n    position: fixed;\n    top: 0;\n    left: 0;\n    z-index: 999;\n    /* width: 100vw; */\n    width: 100%;\n    min-width: 360px;\n    height: 52px;\n    display: flex;\n    align-items: center;\n    justify-content: start;\n    background: ",";\n    box-shadow: 0px 4px 10px 2px rgba(0, 0, 0, 0.12);\n    & > div {\n      width: 100%;\n      display: flex;\n      justify-content: space-around;\n      align-items: center;\n      cursor: default;\n      /* gap: 40px; */\n      /* \uc0c1\ub2e8\ub85c\uace0style */\n      .upper-logo-div {\n        width: 159px;\n        height: 15px;\n        img {\n          width: 160px;\n          object-fit: contain;\n        }\n      }\n      .header-menu {\n        > li:nth-of-type(1) {\n          display: none;\n        }\n        > li:nth-of-type(2) {\n          a {\n            position: relative;\n            font-size: 12px;\n            font-weight: 500;\n            color: ",";\n            > svg {\n              position: absolute;\n              top: 3px;\n              left: -18px;\n              width: 15px;\n              height: 13px;\n              filter: invert(48%) sepia(6%) saturate(7%) hue-rotate(323deg)\n                brightness(98%) contrast(80%);\n            }\n          }\n        }\n        > li:nth-of-type(3) {\n          display: none;\n        }\n      }\n      .loguout-btn {\n        display: none;\n      }\n    }\n  }\n"])),r.Iy.white,r.Iy.header,r.Iy.black,r.Iy.white,r.Iy.header)},2752:(n,e,t)=>{t.d(e,{m:()=>c,v:()=>l});var i,s,o=t(168),r=t(7686),a=t(2291);const c=r.Z.div(i||(i=(0,o.Z)(["\n  .modal {\n    display: none;\n    position: fixed;\n    top: 0;\n    right: 0;\n    bottom: 0;\n    left: 0;\n    z-index: 9999;\n    background-color: rgba(0, 0, 0, 0.3);\n  }\n  .modal-wrapper {\n    width: 784px;\n    height: 796px;\n    margin: 0 auto;\n    border-radius: 10px;\n    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);\n    background-color: ",";\n    /* \ud31d\uc5c5\uc774 \uc5f4\ub9b4\ub54c \uc2a4\ub974\ub975 \uc5f4\ub9ac\ub294 \ud6a8\uacfc */\n    animation: modal-show 0.3s;\n    overflow: hidden;\n  }\n\n  /* \ubaa8\ub2ec \ub0b4\uc6a9 */\n  .modal-content {\n    position: relative;\n    height: 100%;\n    /* padding: 16px; */\n    .btns {\n      display: flex;\n      justify-content: center;\n      gap: 20px;\n      position: absolute;\n      left: calc(50% - 200px);\n      bottom: 40px;\n      z-index: 99999;\n      > button {\n        width: 180px;\n        height: 50px;\n      }\n    }\n  }\n\n  .modal.openModal {\n    display: flex;\n    align-items: center;\n    animation: modal-bg-show 0.1s;\n  }\n  @keyframes modal-show {\n    from {\n      opacity: 0;\n      margin-top: -50px;\n    }\n    to {\n      opacity: 1;\n      margin-top: 0;\n    }\n  }\n  @keyframes modal-bg-show {\n    from {\n      opacity: 0;\n    }\n    to {\n      opacity: 1;\n    }\n  }\n"])),a.Iy.white),l=r.Z.div(s||(s=(0,o.Z)(["\n  .protect-wrap {\n    width: 784px;\n    height: 796px;\n    margin: 0 auto;\n    padding: 40px;\n    > img {\n      /* width: 384px; */\n      width: 100%;\n      height: 30px;\n      padding: 0 150px;\n      object-fit: contain;\n      /* margin: 40px; */\n      margin-bottom: 70px;\n    }\n    .protect-title {\n      display: flex;\n      justify-content: flex-start;\n      align-items: center;\n      margin-bottom: 20px;\n      > img {\n        width: 26px;\n        height: 26px;\n        object-fit: contain;\n      }\n      > span {\n        color: ",";\n        font-size: 24px;\n        font-weight: 500;\n        letter-spacing: -0.36px;\n      }\n    }\n    .content {\n      overflow: auto;\n      width: 704px;\n      height: 463px;\n      border: 1px solid ",";\n      color: ",";\n      font-size: 14px;\n      letter-spacing: -0.21px;\n      padding: 20px;\n      line-height: 1.6;\n      > span {\n        font-weight: 500;\n      }\n      > p {\n        font-weight: 400;\n      }\n    }\n    .footer-text {\n      padding: 10px;\n      /* margin-top: 10px; */\n      font-size: 14px;\n      font-weight: 500;\n      line-height: 1.5;\n    }\n  }\n"])),a.Iy.black,a.Iy.maingray,a.Iy.black)},496:(n,e,t)=>{t.d(e,{$X:()=>c,W$:()=>d,Xs:()=>l});var i,s,o,r=t(168),a=t(7686);const c=a.Z.div(i||(i=(0,r.Z)(["\n  position: relative;\n"]))),l=a.Z.div(s||(s=(0,r.Z)(["\n  width: 1400px;\n  width: 100%;\n  min-width: 280px;\n  display: flex;\n  justify-content: center;\n  margin: 0 auto;\n  margin-top: 100px;\n  padding: 20px 0;\n  & > div {\n    /* width: 1660px; */\n  }\n  @media all and (max-width: 767px) {\n    margin-top: 40px;\n  }\n"]))),d=a.Z.div(o||(o=(0,r.Z)(["\n  margin: 0 auto;\n  width: 1400px;\n  height: 295px;\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  flex-direction: column;\n  gap: 20px;\n\n  & > img {\n    width: 60px;\n    height: 60px;\n    object-fit: contain;\n  }\n  & > P {\n    font-size: 16px;\n    font-weight: 500;\n  }\n  @media all and (max-width: 767px) {\n    margin: 0 auto;\n    width: 360px;\n    height: 295px;\n    display: flex;\n    justify-content: center;\n    align-items: center;\n    flex-direction: column;\n    gap: 20px;\n\n    & > img {\n      width: 30px;\n      height: 30px;\n      object-fit: contain;\n    }\n    & > P {\n      font-size: 12px;\n      font-weight: 500;\n    }\n  }\n"])))}}]);
//# sourceMappingURL=978.76cce500.chunk.js.map