"use strict";(self.webpackChunkgreenjobgo=self.webpackChunkgreenjobgo||[]).push([[844],{4797:(n,e,t)=>{t.d(e,{Z:()=>v});var i,s=t(7313),o=t(6210),r=t(8467),a=t(2135),l=t(3844),c=t(78),d=t(5380);function p(){return p=Object.assign?Object.assign.bind():function(n){for(var e=1;e<arguments.length;e++){var t=arguments[e];for(var i in t)Object.prototype.hasOwnProperty.call(t,i)&&(n[i]=t[i])}return n},p.apply(this,arguments)}function x(n,e){let{title:t,titleId:o,...r}=n;return s.createElement("svg",p({width:17,height:17,viewBox:"0 0 20 20",fill:"none",xmlns:"http://www.w3.org/2000/svg",ref:e,"aria-labelledby":o},r),t?s.createElement("title",{id:o},t):null,i||(i=s.createElement("path",{id:"Vector",d:"M18 16.9206V7.74603C18 7.57847 17.9616 7.4132 17.8878 7.26333C17.8141 7.11345 17.707 6.98308 17.575 6.88254L10.1375 1.21587C9.95359 1.07575 9.72989 1 9.5 1C9.27011 1 9.04641 1.07575 8.8625 1.21587L1.425 6.88254C1.29304 6.98308 1.18594 7.11345 1.11217 7.26333C1.0384 7.4132 1 7.57847 1 7.74603V16.9206C1 17.2069 1.11194 17.4814 1.3112 17.6839C1.51046 17.8863 1.78071 18 2.0625 18H6.3125C6.59429 18 6.86454 17.8863 7.0638 17.6839C7.26306 17.4814 7.375 17.2069 7.375 16.9206V13.6825C7.375 13.3963 7.48694 13.1217 7.6862 12.9193C7.88546 12.7169 8.15571 12.6032 8.4375 12.6032H10.5625C10.8443 12.6032 11.1145 12.7169 11.3138 12.9193C11.5131 13.1217 11.625 13.3963 11.625 13.6825V16.9206C11.625 17.2069 11.7369 17.4814 11.9362 17.6839C12.1355 17.8863 12.4057 18 12.6875 18H16.9375C17.2193 18 17.4895 17.8863 17.6888 17.6839C17.8881 17.4814 18 17.2069 18 16.9206Z",stroke:"#222222",strokeWidth:1.8,strokeLinecap:"round",strokeLinejoin:"round"})))}const h=s.forwardRef(x);t.p;var g=t(3322),m=t(6367),j=t(8605),u=t(259),f=t(9276),b=t(4509),w=t(7307),y=t(6417);const v=()=>{const[n,e]=(0,c.FV)(d.q),[t,i]=(0,s.useState)("businessintro"),[p,x]=(0,s.useState)(!1),[v,C]=(0,s.useState)(""),[k,N]=(0,s.useState)(!1);console.log("select",t);const Z=(0,r.s0)(),A=((0,r.TH)(),(0,m.useMediaQuery)({query:"(max-width: 767px)"})),z=(0,c.rb)(g.AgreeModalAtom),I=(0,c.rb)(j.BusinessPageAtom),L=[{ibt:"b1",type:"businessintro",title:"\ud648",icon:(0,y.jsx)(h,{})},{ibt:"b2",type:"portpoliolist",title:"\uc218\uac15\uc0dd \ud3ec\ud2b8\ud3f4\ub9ac\uc624",icon:(0,y.jsx)(u.G,{icon:f.nfZ})},{ibt:"b3",type:"jobmanagerlist",title:"\ucde8\uc5c5 \ub2f4\ub2f9\uc790 \uc548\ub0b4",icon:(0,y.jsx)(u.G,{icon:f.ILF})}];(0,s.useEffect)((()=>{x(!!v)}),[v]);const{pathname:E}=(0,r.TH)();return(0,s.useEffect)((()=>{E.includes("portpoliolist")?i("portpoliolist"):E.includes("jobmanagerlist")?i("jobmanagerlist"):i("businessintro")}),[E]),(0,y.jsxs)(y.Fragment,{children:[(0,y.jsx)(o.w,{children:(0,y.jsxs)("div",{className:"business-header",children:[(0,y.jsx)("div",{className:"upper-logo-div",onClick:()=>{i("businessintro")},children:A?(0,y.jsx)("img",{src:"".concat("","/assets/LoginTitle.png"),alt:"greenlogo"}):(0,y.jsx)(a.rU,{to:"/business",children:(0,y.jsx)("img",{src:"".concat("","/assets/LoginTitle.png"),alt:"greenlogo"})})}),(0,y.jsx)("ul",{className:"header-menu",children:L.map((n=>(0,y.jsx)("li",{onClick:()=>{return e=n.type,i(e),void I();var e},className:"".concat(t===n.type?"select":""),children:(0,y.jsxs)(a.rU,{to:"./".concat(n.type),children:[n.icon," ",n.title]})},n.ibt)))}),(0,y.jsxs)("div",{className:"loguout-btn",onClick:()=>{N(!0)},children:["\ub85c\uadf8\uc544\uc6c3",(0,y.jsx)("img",{src:"".concat("","/assets/LogoutIcon.svg"),alt:"logout"})]})]})}),p&&(0,y.jsx)(w.Z,{header:"",open:p,close:()=>{x(!1),C("")},onConfirm:()=>{x(!1),C("")},children:(0,y.jsx)("span",{children:v})}),k&&(0,y.jsx)(b.Z,{open:k,close:()=>{N(!1)},onConfirm:()=>{try{z(),I(),(0,l.L2)(),e((n=>({...n,isLogin:!1,accessToken:null,role:"",id:""}))),Z("/")}catch(n){C("\ub85c\uadf8\uc544\uc6c3\uc774 \uc815\uc0c1\uc801\uc73c\ub85c \ucc98\ub9ac\ub418\uc9c0 \uc54a\uc558\uc2b5\ub2c8\ub2e4.")}},onCancel:()=>{N(!1)},children:(0,y.jsx)("span",{children:"\ub85c\uadf8\uc544\uc6c3 \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?"})})]})}},4509:(n,e,t)=>{t.d(e,{Z:()=>o});t(7313);var i=t(3607),s=t(6417);const o=n=>{let{header:e,open:t,close:o,onConfirm:r,onCancel:a,children:l}=n;return(0,s.jsx)(i.R9,{onClick:o,children:(0,s.jsx)("div",{className:t?"openConfirmModal Confimmodal":"Confirmmodal",children:t&&(0,s.jsxs)("div",{className:"modalConfirm-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:[(0,s.jsxs)("div",{className:"modal-header",children:[e,(0,s.jsx)("p",{className:"close",onClick:o})]}),(0,s.jsxs)("div",{className:"modalConfirm-content",children:[l,(0,s.jsxs)("div",{children:[(0,s.jsx)(i.MV,{onClick:a,children:"\ucde8\uc18c"}),(0,s.jsx)(i.Us,{onClick:r,children:"\ud655\uc778"})]})]})]})})})}},6505:(n,e,t)=>{t.d(e,{Z:()=>r});t(7313);var i=t(21),s=t(3607),o=t(6417);const r=n=>{let{close:e,open:t,onConfirm:r,onCancel:a,children:l}=n;return(0,o.jsx)(i.m,{onClick:e,children:(0,o.jsx)("div",{className:t?"openModal modal":"modal",children:t?(0,o.jsx)("div",{className:"modal-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:(0,o.jsxs)("div",{className:"modal-content",children:[l,(0,o.jsxs)("div",{className:"btns",children:[(0,o.jsx)(s.MV,{onClick:a,children:"\ucde8\uc18c"}),(0,o.jsx)(s.Us,{onClick:r,children:"\ud655\uc778"})]})]})}):null})})}},3322:(n,e,t)=>{t.r(e),t.d(e,{AgreeModalAtom:()=>j,default:()=>u});var i=t(7313),s=t(8467),o=t(4797),r=t(9459),a=t(9919),l=t(6505),c=t(78),d=t(451),p=t(3844),x=t(4509),h=t(6367),g=t(6417);const{persistAtom:m}=(0,d.J)();c.zl.RECOIL_DUPLICATE_ATOM_KEY_CHECKING_ENABLED=!1;const j=(0,c.cn)({key:"AgreeModalAtom",default:{isBizAgree:!1},effects_UNSTABLE:[m]}),u=()=>{const[n,e]=(0,i.useState)(!0),[t,d]=(0,i.useState)(!1),[m,u]=(0,c.FV)(j),f=(0,h.useMediaQuery)({query:"(max-width: 767px)"}),b=(0,s.s0)(),w=async()=>{d(!0)};return(0,i.useEffect)((()=>{f&&(b("/business/portpoliolist"),u({isBizAgree:!0}))}),[f]),(0,g.jsxs)(r.$X,{children:[(0,g.jsx)(o.Z,{}),(0,g.jsx)(r.Xs,{children:(0,g.jsx)(s.j3,{})}),!m.isBizAgree&&(0,g.jsx)(l.Z,{close:w,open:n,onConfirm:()=>{u({isBizAgree:!0}),e(!1)},onCancel:w,children:(0,g.jsx)(a.Z,{})}),t&&(0,g.jsx)(x.Z,{header:"",open:t,close:()=>{d(!1)},onConfirm:async()=>{d(!1),u({isBizAgree:!1});try{await(0,p.L2)(),b("/")}catch(n){console.error("Error during logout:",n)}},onCancel:()=>{d(!1)},children:(0,g.jsx)("span",{children:"\ub3d9\uc758\ub97c \ud558\uc9c0 \uc54a\ub294 \uacbd\uc6b0 \ud574\ub2f9 \uc0ac\uc774\ud2b8\ub97c \uc774\uc6a9\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4."})})]})}},9919:(n,e,t)=>{t.d(e,{Z:()=>o});t(7313);var i=t(21),s=t(6417);const o=()=>(0,s.jsx)(i.v,{children:(0,s.jsxs)("div",{className:"protect-wrap",children:[(0,s.jsx)("img",{src:"".concat("","/assets/LoginTitle.png"),alt:"greenlogo"}),(0,s.jsxs)("div",{className:"protect-title",children:[(0,s.jsx)("img",{src:"".concat("","/assets/checkIcon.png"),alt:"check"}),(0,s.jsx)("span",{children:" \uac1c\uc778\uc815\ubcf4 \ubcf4\ud638 \uc11c\uc57d\uc11c "})]}),(0,s.jsxs)("div",{className:"content",children:[(0,s.jsx)("span",{children:"1.\uc11c\uc57d \ub0b4\uc6a9 \ubc0f \ubaa9\uc801"}),(0,s.jsx)("p",{children:"1.1 \uc81c\uacf5\ubc1b\uc740 \ud6c8\ub828\uc0dd \uac1c\uc778\uc815\ubcf4\ub97c \ucc44\uc6a9 \uc774\uc678 \ub2e4\ub978 \uc5b4\ub5a0\ud55c \uacbd\uc6b0\uc5d0\ub3c4 \ud5c8\uac00 \uc5c6\uc774 \uc0ac\uc6a9\ud558\uc9c0 \uc54a\uaca0\uc74c\uc744 \uba85\uc2dc\uc801\uc73c\ub85c \uc11c\uc57d\ud569\ub2c8\ub2e4."}),(0,s.jsx)("br",{}),(0,s.jsx)("span",{children:"2.\uac1c\uc778\uc815\ubcf4 \uc0ac\uc6a9 \uc81c\ud55c"}),(0,s.jsx)("p",{children:"2.1 \ucc44\uc6a9\ubaa9\uc801 \uc678\uc5d0 \uc0ac\uc6a9\ud560 \uacbd\uc6b0, \uc0ac\uc804 \ub3d9\uc758\ub97c \uc5bb\ub294 \ub4f1 \uad00\ub828 \ubc95\ub839 \ubc0f \uaddc\uc815\uc744 \uc900\uc218\ud560 \uac83\uc744 \uc11c\uc57d\ud569\ub2c8\ub2e4."})," ",(0,s.jsx)("br",{}),(0,s.jsx)("span",{children:"3.\uac1c\uc778\uc815\ubcf4\uc758 \ubcf4\uc548 \uc870\uce58"}),(0,s.jsx)("p",{children:"3.1 \ud6c8\ub828\uc0dd \uac1c\uc778\uc815\ubcf4\ub294 \uc548\uc804\ud558\uac8c \uad00\ub9ac\ud558\uace0 \uc720\ucd9c\uc744 \ubc29\uc9c0\ud558\uae30 \uc704\ud558\uc5ec \uc801\uc808\ud55c \ubcf4\uc548 \uc870\uce58\ub97c \ucde8\ud558\uaca0\uc2b5\ub2c8\ub2e4."}),(0,s.jsx)("p",{children:"3.2 \ud574\ub2f9 \uac1c\uc778\uc815\ubcf4\ub97c \uc5f4\ub78c\ud560 \uad8c\ud55c\uc774 \uc788\ub294 \uc9c1\uc6d0\uc740 \ucd5c\uc18c\ud55c\uc73c\ub85c \uc81c\ud55c\ud558\uace0, \uc5c4\uaca9\ud55c \uc811\uadfc \uc81c\uc5b4\ub97c \uc2dc\ud589\ud558\uaca0\uc2b5\ub2c8\ub2e4."})," ",(0,s.jsx)("br",{}),(0,s.jsx)("span",{children:"4.\uc81c3\uc790 \uc81c\uacf5 \ubc0f \uc678\ubd80 \uc81c\ucd9c \uae08\uc9c0"}),(0,s.jsx)("p",{children:"4.1 \ud6c8\ub828\uc0dd \uac1c\uc778\uc815\ubcf4\ub97c \uc5b4\ub5a0\ud55c \uacbd\uc6b0\uc5d0\ub3c4 \uc81c3\uc790\uc5d0\uac8c \uc81c\uacf5\ud558\uac70\ub098 \uc678\ubd80\ub85c \uc81c\ucd9c\ud558\uc9c0 \uc54a\uaca0\uc74c\uc744 \uba85\uc2dc\uc801\uc73c\ub85c \uc11c\uc57d\ud569\ub2c8\ub2e4."})," ",(0,s.jsx)("br",{}),(0,s.jsx)("span",{children:"5.\uac1c\uc778\uc815\ubcf4 \ud30c\uae30"}),(0,s.jsx)("p",{children:"5.1 \ud6c8\ub828\uc0dd \uac1c\uc778\uc815\ubcf4\ub294 \uc218\uc9d1 \ubc0f \uc774\uc6a9 \ubaa9\uc801\uc774 \ub2ec\uc131\ub41c \ud6c4 \uc9c0\uccb4 \uc5c6\uc774 \ud30c\uae30\ub418\uac70\ub098, \ud6c8\ub828\uc0dd\uc774 \uac1c\uc778\uc815\ubcf4\uc758 \ud30c\uae30\ub97c \uc694\uccad\ud55c \uacbd\uc6b0\uc5d0\ub294 \uc989\uc2dc \ud30c\uae30\ud558\uaca0\uc2b5\ub2c8\ub2e4."})," ",(0,s.jsx)("br",{}),(0,s.jsx)("span",{children:"6.\ubc95\ub839 \uc900\uc218"}),(0,s.jsx)("p",{children:"6.1 \ubcf8\uc778\uc740 \uad6d\ub0b4 \ubc0f \uad6d\uc81c\uc801\uc73c\ub85c \uc801\uc6a9\ub418\ub294 \uac1c\uc778\uc815\ubcf4 \ubcf4\ud638 \uad00\ub828 \ubc95\ub839 \ubc0f \uaddc\uc815\uc744 \uc900\uc218\ud558\uaca0\uc2b5\ub2c8\ub2e4."})," ",(0,s.jsx)("br",{}),(0,s.jsx)("span",{children:"7.\uc11c\uc57d\uc758 \ud6a8\ub825 \ubc0f \ubcc0\uacbd"}),(0,s.jsx)("p",{children:"7.1 \ubcf8 \uc11c\uc57d\uc740 \uc11c\uba85\ub41c \uc2dc\uc810\ubd80\ud130 \ud6a8\ub825\uc774 \ubc1c\uc0dd\ud558\uba70, \ub0b4\uc6a9\uc774 \ubcc0\uacbd\ub418\ub294 \uacbd\uc6b0\uc5d0\ub294 \ud6c8\ub828\uc0dd\uc5d0\uac8c \uc0ac\uc804 \ud1b5\uc9c0\ud55c \ud6c4 \ubcc0\uacbd\ub420 \uc218 \uc788\uc2b5\ub2c8\ub2e4."})," ",(0,s.jsx)("br",{}),(0,s.jsx)("span",{children:"8.\ubbfc\uc6d0 \ucc98\ub9ac \ubc0f \uc751\ub300 \ucc45\uc784"}),(0,s.jsx)("p",{children:"8.1 \ud6c8\ub828\uc0dd\uc73c\ub85c\ubd80\ud130\uc758 \uac1c\uc778\uc815\ubcf4 \uad00\ub828 \ubbfc\uc6d0 \ubc0f \ubb38\uc758\uc5d0 \uc2e0\uc18d\ud558\uace0 \uc801\uc808\ud558\uac8c \ub300\uc751\ud558\uaca0\uc2b5\ub2c8\ub2e4."})," ",(0,s.jsx)("br",{})]}),(0,s.jsx)("div",{className:"footer-text",children:"\ubcf8\uc778\uc740 \ubcf8 \uc11c\uc57d\uc758 \ub0b4\uc6a9\uc744 \uc219\uc9c0\ud558\uc600\uc73c\uba70, \uc5b4\ub5a0\ud55c \uacbd\uc6b0\uc5d0\ub3c4 \ud6c8\ub828\uc0dd \uac1c\uc778\uc815\ubcf4\ub97c \ubb34\ub2e8\uc73c\ub85c \uc0ac\uc6a9\ud558\uc9c0 \uc54a\uaca0\ub2e4\ub294 \uac83\uc5d0 \ub3d9\uc758\ud569\ub2c8\ub2e4."})]})})},6210:(n,e,t)=>{t.d(e,{w:()=>a});var i,s=t(168),o=t(5112),r=t(3607);const a=o.Z.div(i||(i=(0,s.Z)(["\n  position: fixed;\n  top: 0;\n  left: 0;\n  z-index: 999;\n  width: 100vw;\n  /* max-width: 196rem; */\n  height: 100px;\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  background: ",";\n  box-shadow: 0px 4px 10px 2px rgba(0, 0, 0, 0.12);\n  & > div {\n    width: 1400px;\n    display: flex;\n    justify-content: space-between;\n    align-items: center;\n    cursor: pointer;\n    /* \uc0c1\ub2e8\ub85c\uace0style */\n    .upper-logo-div {\n      width: 256px;\n      height: 20px;\n      img {\n        width: 100%;\n        object-fit: contain;\n      }\n    }\n    .header-menu {\n      display: flex;\n      justify-content: center;\n      align-items: center;\n      gap: 90px;\n      li {\n        > a {\n          position: relative;\n          font-size: 16px;\n          font-weight: 500;\n          color: ",";\n          > svg {\n            position: absolute;\n            top: 3px;\n            left: -25px;\n            width: 17px;\n            height: 17px;\n            filter: invert(48%) sepia(6%) saturate(7%) hue-rotate(323deg)\n              brightness(98%) contrast(80%);\n          }\n        }\n      }\n      .select {\n        > a {\n          color: ",";\n          > svg {\n            filter: invert(6%) sepia(79%) saturate(16%) hue-rotate(349deg)\n              brightness(91%) contrast(85%);\n          }\n        }\n      }\n    }\n    .loguout-btn {\n      font-size: 15px;\n      font-weight: 500;\n      > img {\n        width: 17px;\n        height: 17px;\n        margin-left: 8px;\n      }\n    }\n  }\n  /* \ubc18\uc751\ud615 \ud5e4\ub354 */\n  @media all and (max-width: 840px) {\n    position: fixed;\n    top: 0;\n    left: 0;\n    z-index: 999;\n    /* width: 100vw; */\n    width: 100%;\n    min-width: 360px;\n    height: 52px;\n    display: flex;\n    align-items: center;\n    justify-content: start;\n    background: ",";\n    box-shadow: 0px 4px 10px 2px rgba(0, 0, 0, 0.12);\n    & > div {\n      width: 100%;\n      display: flex;\n      justify-content: space-around;\n      align-items: center;\n      cursor: default;\n      /* gap: 40px; */\n      /* \uc0c1\ub2e8\ub85c\uace0style */\n      .upper-logo-div {\n        width: 159px;\n        height: 15px;\n        img {\n          width: 160px;\n          object-fit: contain;\n        }\n      }\n      .header-menu {\n        > li:nth-of-type(1) {\n          display: none;\n        }\n        > li:nth-of-type(2) {\n          a {\n            position: relative;\n            font-size: 12px;\n            font-weight: 500;\n            color: ",";\n            > svg {\n              position: absolute;\n              top: 3px;\n              left: -18px;\n              width: 15px;\n              height: 13px;\n              filter: invert(48%) sepia(6%) saturate(7%) hue-rotate(323deg)\n                brightness(98%) contrast(80%);\n            }\n          }\n        }\n        > li:nth-of-type(3) {\n          display: none;\n        }\n      }\n      .loguout-btn {\n        display: none;\n      }\n    }\n  }\n"])),r.Iy.white,r.Iy.header,r.Iy.black,r.Iy.white,r.Iy.header)},21:(n,e,t)=>{t.d(e,{m:()=>l,v:()=>c});var i,s,o=t(168),r=t(5112),a=t(3607);const l=r.Z.div(i||(i=(0,o.Z)(["\n  .modal {\n    display: none;\n    position: fixed;\n    top: 0;\n    right: 0;\n    bottom: 0;\n    left: 0;\n    z-index: 9999;\n    background-color: rgba(0, 0, 0, 0.6);\n    backdrop-filter: blur(6px);\n  }\n  .modal-wrapper {\n    width: 784px;\n    height: 796px;\n    margin: 0 auto;\n    border-radius: 10px;\n    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);\n    background-color: ",";\n    /* \ud31d\uc5c5\uc774 \uc5f4\ub9b4\ub54c \uc2a4\ub974\ub975 \uc5f4\ub9ac\ub294 \ud6a8\uacfc */\n    animation: modal-show 0.3s;\n    overflow: hidden;\n  }\n\n  /* \ubaa8\ub2ec \ub0b4\uc6a9 */\n  .modal-content {\n    position: relative;\n    height: 100%;\n    /* padding: 16px; */\n    .btns {\n      display: flex;\n      justify-content: center;\n      gap: 20px;\n      position: absolute;\n      left: calc(50% - 200px);\n      bottom: 40px;\n      z-index: 99999;\n      > button {\n        width: 180px;\n        height: 50px;\n      }\n    }\n  }\n\n  .modal.openModal {\n    display: flex;\n    align-items: center;\n    animation: modal-bg-show 0.1s;\n  }\n  @keyframes modal-show {\n    from {\n      opacity: 0;\n      margin-top: -50px;\n    }\n    to {\n      opacity: 1;\n      margin-top: 0;\n    }\n  }\n  @keyframes modal-bg-show {\n    from {\n      opacity: 0;\n    }\n    to {\n      opacity: 1;\n    }\n  }\n"])),a.Iy.white),c=r.Z.div(s||(s=(0,o.Z)(["\n  .protect-wrap {\n    width: 784px;\n    height: 796px;\n    margin: 0 auto;\n    padding: 40px;\n    > img {\n      /* width: 384px; */\n      width: 100%;\n      height: 30px;\n      padding: 0 150px;\n      object-fit: contain;\n      /* margin: 40px; */\n      margin-bottom: 70px;\n    }\n    .protect-title {\n      display: flex;\n      justify-content: flex-start;\n      align-items: center;\n      margin-bottom: 20px;\n      > img {\n        width: 26px;\n        height: 26px;\n        object-fit: contain;\n      }\n      > span {\n        color: ",";\n        font-size: 24px;\n        font-weight: 500;\n        letter-spacing: -0.36px;\n      }\n    }\n    .content {\n      overflow: auto;\n      width: 704px;\n      height: 463px;\n      border: 1px solid ",";\n      color: ",";\n      font-size: 14px;\n      letter-spacing: -0.21px;\n      padding: 20px;\n      line-height: 1.6;\n      > span {\n        font-weight: 500;\n      }\n      > p {\n        font-weight: 400;\n      }\n    }\n    .footer-text {\n      padding: 10px;\n      /* margin-top: 10px; */\n      font-size: 14px;\n      font-weight: 500;\n      line-height: 1.5;\n    }\n  }\n"])),a.Iy.black,a.Iy.maingray,a.Iy.black)}}]);