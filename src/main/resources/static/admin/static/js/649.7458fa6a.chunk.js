"use strict";(self.webpackChunkgreenjobgo_admin=self.webpackChunkgreenjobgo_admin||[]).push([[649],{3649:(n,e,t)=>{t.r(e),t.d(e,{default:()=>f});var i=t(2791),p=t(3570),o=t(4261),r=t(3725),s=t(6064),d=t(6381),l=t(9806),a=t(1632),x=t(7689),c=t(4571),h=t(5779),g=t(184);const f=()=>{const[n,e]=(0,i.useState)(!1),[t,f]=(0,i.useState)(""),u=(0,x.s0)(),[y,m]=(0,i.useState)(!1),[b,w]=(0,i.useState)(null),[j,k]=(0,i.useState)(""),[I,v]=(0,i.useState)(""),[z,N]=(0,i.useState)(2),[S,C]=(0,i.useState)(0),[Z,U]=(0,i.useState)(!1),[F,D]=(0,i.useState)(!1),[L,M]=(0,i.useState)(!1),[O,A]=(0,i.useState)(0),[G,_]=(0,i.useState)(""),[R,V]=(0,i.useState)(!1),[W,E]=(0,i.useState)({userDetail:"",birth:"",certificateValue:"",subject:""}),[P,Y]=(0,i.useState)({thumbNail:"",resume:"",portFolio:[],fileLinks:[]}),B=(0,x.TH)().state;console.log("edit",B),(0,i.useEffect)((()=>{(0,d.M1)(B.istudent,E,Y)}),[W.istudent]);const H=async n=>{C(n),M(!0)},J=(n,e)=>{n.target.checked?(_([e]),A(0),V(!0),console.log(G)):(_([e]),A(1),V(!0),console.log(G))};return(0,i.useEffect)((()=>{e(!!t)}),[t]),(0,g.jsxs)(p.jO,{children:[L&&(0,g.jsx)(s.cf,{deleteOkModal:L,handleOkClick:async()=>{try{!0===(await(0,d._I)(S)).success&&(M(!1),(0,d.M1)(B.istudent,E,Y))}catch(n){M(!1),f("\uc815\uc0c1\uc801\uc73c\ub85c \ucc98\ub9ac \ub418\uc9c0 \uc54a\uc558\uc2b5\ub2c8\ub2e4.")}},handleCancelClick:()=>{M(!1)}}),R&&(0,g.jsx)(h.Z,{open:R,close:()=>V(!1),onConfirm:()=>{(0,d.BJ)(B.istudent,G,O,f),V(!1)},onCancel:()=>V(!1),children:(0,g.jsx)("span",{children:"\ub300\ud45c \ud3ec\ud2b8\ud3f4\ub9ac\uc624\ub85c \ub4f1\ub85d \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?"})}),(0,g.jsxs)("ul",{className:"portfolio-list",children:[(0,g.jsxs)("li",{children:[(0,g.jsx)("h2",{children:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624"}),(0,g.jsx)("button",{onClick:()=>{U(!0)},className:"upload-btn",children:"+ \ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ud30c\uc77c \ub610\ub294 \ub9c1\ud06c \ucd94\uac00"})]}),(0,g.jsxs)("li",{children:[(0,g.jsx)("div",{children:P&&P.portFolio&&P.portFolio.length>0?P.portFolio.map((n=>(0,g.jsxs)("div",{className:"portfolio-box",children:[(0,g.jsxs)("div",{className:"portfolio-inner",children:[(0,g.jsxs)("div",{className:"portfolio-top",children:[(0,g.jsxs)("div",{children:[(0,g.jsx)("p",{children:(0,g.jsx)(l.G,{icon:a.gSj})}),(0,g.jsxs)("a",{href:"https://".concat(n.file),target:"_blank",rel:"noopener noreferrer",children:["\xa0",n.file]})]}),(0,g.jsxs)("div",{className:"portfolio-icons",children:[1===n.mainYn?(0,g.jsx)("p",{className:"main-pofol-icon",children:(0,g.jsx)(l.G,{icon:a.nmd})}):"",(0,g.jsx)("p",{className:"delete-icon",children:(0,g.jsx)(l.G,{onClick:()=>H(n.ifile),icon:a.WA2})})]})]}),(0,g.jsx)("div",{className:"portfolio-btm",children:(0,g.jsx)("span",{children:n.oneWord})})]}),(0,g.jsx)("div",{className:"main-check",children:(0,g.jsx)("input",{type:"checkbox",value:n.ifile,checked:1===n.mainYn,onChange:e=>J(e,n.ifile)})})]},(0,o.Z)()))):(0,g.jsx)("div",{children:(0,g.jsx)("div",{className:"portfolio-zero",children:(0,g.jsx)("span",{children:"\ub4f1\ub85d\ub41c \ud3ec\ud2b8\ud3f4\ub9ac\uc624 PDF \ud30c\uc77c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4."})})})}),(0,g.jsx)("div",{children:P&&P.fileLinks&&P.fileLinks.length>0?P.fileLinks.map((n=>(0,g.jsxs)("div",{className:"portfolio-box",children:[(0,g.jsxs)("div",{className:"portfolio-inner",children:[(0,g.jsxs)("div",{className:"portfolio-top",children:[(0,g.jsxs)("div",{children:[(0,g.jsx)("p",{children:(0,g.jsx)(l.G,{icon:a.nNP})}),(0,g.jsxs)("a",{href:"https://".concat(n.fileLink),target:"_blank",rel:"noopener noreferrer",children:["\xa0",n.fileLink]})]}),(0,g.jsx)("div",{children:(0,g.jsx)("p",{className:"delete-icon",children:(0,g.jsx)(l.G,{onClick:()=>H(n.ifile),icon:a.WA2,style:{color:"#6d6d6d"}})})})]}),(0,g.jsx)("div",{className:"portfolio-btm",children:(0,g.jsx)("span",{children:n.oneWord})})]}),(0,g.jsx)("div",{className:"main-check",children:(0,g.jsx)("input",{type:"checkbox",value:n.ifile,checked:1===n.mainYn,onChange:e=>J(e,n.ifile)})})]},(0,o.Z)()))):(0,g.jsx)("div",{children:(0,g.jsx)("div",{className:"portfolio-zero",children:(0,g.jsx)("span",{children:"\ub4f1\ub85d\ub41c \ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ub9c1\ud06c\uac00 \uc5c6\uc2b5\ub2c8\ub2e4."})})})})]})]}),(0,g.jsx)("div",{className:"buttons",children:(0,g.jsx)("div",{children:(0,g.jsx)(g.Fragment,{children:(0,g.jsx)("button",{onClick:()=>{u("/admin/student/".concat(B.istudent))},children:"\ud655\uc778"})})})}),Z&&(0,g.jsx)(r.M1,{modalOpen:Z,handlePofolModalCancel:()=>{U(!1)},iFile:z,setIFile:N,selectedFile:b,setSelectedFile:w,description:j,setDescription:k,linkUrl:I,setLinkUrl:v,handleFileUpload:async()=>{if(z&&(2===z||3===z)){let e=new FormData;e.append("file",b);try{const n=await(0,d.dS)(B.istudent,z,e,j,I,f);m(n),!0===n.success&&(U(!1),D(!0),N(2),w(null),v(""),k(""),(0,d.M1)(B.istudent,E,Y))}catch(n){U(!1),D(!0),N(2),w(null),v(""),k(""),(0,d.M1)(B.istudent,E,Y)}}}}),F&&(0,g.jsx)(s.py,{acceptOkModal:F,setAcceptOkModal:D,uploadResult:y}),n&&(0,g.jsx)(c.Z,{header:"",open:n,close:()=>{e(!1)},onConfirm:()=>{e(!1)},children:(0,g.jsx)("span",{children:t})})]})}},3570:(n,e,t)=>{t.d(e,{XO:()=>h,cA:()=>g,eI:()=>x,jO:()=>f,xd:()=>c});var i,p,o,r,s,d=t(168),l=t(456),a=t(2291);const x=l.Z.div(i||(i=(0,d.Z)(["\n  /* padding: 30px 25px 0px 92px; */\n  height: 100%;\n  .title > h2 {\n    font-size: 24px;\n    font-weight: 700;\n    letter-spacing: -0.96px;\n    margin-top: 30px;\n    margin-left: 92px;\n    margin-bottom: 30px;\n  }\n  .info-contain {\n    margin-left: 92px;\n    margin-right: 25px;\n    padding-right: 92px;\n    overflow-y: auto;\n    height: 600px;\n  }\n  .buttons {\n    border-top: 1px solid ",";\n    display: flex;\n    justify-content: center;\n    gap: 10px;\n    width: 100%;\n    padding: 45px 0;\n    > button {\n      width: 131px;\n      height: 50px;\n      color: ",";\n      background: ",";\n      border: none;\n      border-radius: 4px;\n      cursor: pointer;\n      :first-of-type {\n        color: ",";\n        background: ",";\n      }\n    }\n  }\n"])),a.Iy.grayLight,a.Iy.white,a.Iy.admintxt,a.Iy.white,a.Iy.btn),c=l.Z.ul(p||(p=(0,d.Z)(["\n  display: flex;\n  gap: 40px;\n  padding-bottom: 40px;\n  border-bottom: 1px solid ",";\n  color: #222;\n  li:first-of-type {\n    max-width: 300px;\n    height: 240px;\n    img {\n      width: 100%;\n    }\n  }\n  .info-content-left {\n    font-size: 16px;\n    width: 450px;\n    div {\n      :not(:last-of-type) {\n        margin-bottom: 20px;\n      }\n      :not(:first-of-type) {\n        span {\n          :first-of-type {\n            font-size: 17px;\n            font-weight: 600;\n          }\n        }\n      }\n      :nth-of-type(1) {\n        display: flex;\n        align-items: end;\n        gap: 8px;\n        /* margin-bottom: 40px; */\n        > input {\n          border: 1px solid ",";\n          border-radius: 4px;\n          padding: 2px 6px;\n          font-size: 18px;\n          width: 100px;\n          height: 25px;\n        }\n        .student-name {\n          font-size: 22px;\n          font-weight: 500;\n          letter-spacing: -0.3px;\n        }\n        .student-age {\n          display: flex;\n          justify-content: center;\n          gap: 10px;\n          font-size: 16px;\n          font-weight: 500;\n          color: ",";\n          letter-spacing: -0.24px;\n          input {\n            border: 1px solid ",";\n            border-radius: 4px;\n            padding: 2px 8px;\n            width: 40px;\n            height: 25px;\n            padding-left: 3px;\n            line-height: 20px;\n          }\n        }\n      }\n      :nth-of-type(2) {\n        display: flex;\n        align-items: center;\n        white-space: nowrap;\n        overflow: hidden;\n        text-overflow: ellipsis;\n        gap: 21px;\n      }\n      :nth-of-type(3) {\n        display: flex;\n        align-items: center;\n        gap: 21px;\n        > span {\n          :first-of-type {\n            margin-right: 15.4px;\n          }\n        }\n        > input {\n          border: 1px solid ",";\n          border-radius: 4px;\n          padding: 2px 8px;\n          width: 350px;\n          height: 22px;\n          padding-left: 3px;\n          line-height: 20px;\n        }\n      }\n      :nth-of-type(4) {\n        display: flex;\n        align-items: center;\n        gap: 21.6px;\n        > input {\n          border: 1px solid ",";\n          border-radius: 4px;\n          padding: 2px 8px;\n          width: 350px;\n          height: 22px;\n          padding-left: 3px;\n          line-height: 20px;\n        }\n      }\n      :nth-of-type(5) {\n        display: flex;\n        justify-content: flex-start;\n        align-items: flex-start;\n        width: 980px;\n        height: 26px;\n        > span {\n          margin-right: 21px;\n        }\n        .hash-tag-wrap {\n          .hash-tag-inner {\n            > .hash-tag-input {\n              padding: 5px 3px 3px 0;\n              font-size: 16px;\n              width: 300px;\n              display: inline-flex;\n              cursor: text;\n              height: 26px;\n              border: none;\n            }\n            > .tags {\n              display: flex;\n              justify-content: space-between;\n              align-items: flex-start;\n              gap: 5px;\n              font-size: 16px;\n              font-weight: 400;\n              color: ",";\n              background: ",";\n              border-radius: 6px;\n              padding: 5px 5px 5px 10px;\n              margin: 0;\n              > img {\n                width: 11px;\n                height: 11px;\n                cursor: pointer;\n              }\n            }\n          }\n        }\n        .read-hashtag {\n          display: flex;\n          justify-content: flex-start;\n          align-items: center;\n          flex-wrap: wrap;\n          > div {\n            height: 26px;\n            display: flex;\n            align-items: center;\n            color: ",";\n            background: ",";\n            border-radius: 6px;\n            padding: 5px 10px;\n            margin: 0;\n            > span {\n              font-size: 16px;\n              font-weight: 400;\n              margin: 0;\n            }\n          }\n        }\n      }\n    }\n  }\n  .info-content-right {\n    width: 50%;\n    height: 22%;\n    div {\n      font-size: 16px;\n      > input {\n        border: 1px solid ",";\n        border-radius: 4px;\n        padding: 2px 8px;\n        width: 350px;\n        height: 22px;\n        padding-left: 3px;\n        line-height: 20px;\n      }\n      > span {\n        :first-of-type {\n          font-size: 16px;\n          font-weight: 700;\n        }\n      }\n      :not(:last-of-type) {\n        font-size: 16px;\n        font-weight: 400;\n        margin-bottom: 24px;\n      }\n      :nth-of-type(1) {\n        display: flex;\n        align-items: end;\n        gap: 18px;\n        margin-bottom: 26px;\n      }\n      :nth-of-type(2) {\n        display: flex;\n        align-items: center;\n        gap: 18px;\n      }\n      :nth-of-type(3) {\n        display: flex;\n        align-items: center;\n        gap: 34px;\n      }\n      :nth-of-type(4) {\n        display: flex;\n        align-items: center;\n        gap: 50px;\n      }\n    }\n  }\n"])),a.Iy.header,a.Iy.input,a.Iy.btn,a.Iy.input,a.Iy.input,a.Iy.input,a.Iy.black,a.Iy.search,a.Iy.black,a.Iy.search,a.Iy.input),h=l.Z.div(o||(o=(0,d.Z)(["\n  height: 310px;\n  /* border-bottom: 1px solid ","; */\n  padding: 40px 0;\n  > ul {\n    > li {\n      > h2 {\n        font-size: 20px;\n        font-weight: 700;\n        margin-bottom: 30px;\n        letter-spacing: -0.27px;\n      }\n      > h3 {\n        font-size: 18px;\n        font-weight: 500;\n        margin-bottom: 14px;\n        letter-spacing: -0.27px;\n      }\n      :nth-of-type(2) {\n        .oneword {\n          > input {\n            width: 690px;\n            height: 40px;\n            border: 1px solid ",";\n            padding: 0 10px;\n            border-radius: 4px;\n          }\n          > p {\n            color: ",";\n            padding: 8px 0 0 4px;\n          }\n        }\n      }\n      :nth-of-type(3) {\n        margin-top: 30px;\n        .file-box {\n          display: flex;\n          justify-content: space-between;\n          align-items: center;\n          width: 690px;\n          height: 40px;\n          line-height: 40px;\n          background: ",";\n          border-radius: 6px;\n          padding: 0px 13px 0 10px;\n          a {\n            font-size: 14px;\n          }\n          p {\n            font-size: 21px;\n            height: 21px;\n            line-height: 21px;\n            cursor: pointer;\n          }\n          div {\n            display: flex;\n            align-items: center;\n          }\n        }\n        .edit-file-box {\n          display: flex;\n          align-items: center;\n          gap: 10px;\n          .upload-name {\n            display: inline-block;\n            width: 570px;\n            height: 40px;\n            line-height: 40px;\n            padding: 0 10px;\n            border: 1px solid ",";\n            border-radius: 4px;\n            color: ",";\n          }\n          label {\n            display: inline-block;\n            width: 110px;\n            height: 40px;\n            line-height: 40px;\n            color: ",";\n            border-radius: 6px;\n            text-align: center;\n            background-color: ",';\n            cursor: pointer;\n          }\n          input[type="file"] {\n            position: absolute;\n            width: 0;\n            height: 0;\n            padding: 0;\n            overflow: hidden;\n            border: 0;\n          }\n          > div {\n            display: flex;\n            gap: 5px;\n            padding-left: 10px;\n            button {\n              width: 85px;\n              height: 40px;\n              border: none;\n              border-radius: 6px;\n              cursor: pointer;\n              :first-of-type {\n                color: ',";\n                background: ",";\n              }\n              :last-of-type {\n                color: ",";\n                background: ",";\n                border: 1px solid ",";\n              }\n            }\n          }\n        }\n      }\n    }\n  }\n"])),a.Iy.header,a.Iy.input,a.Iy.red,a.Iy.search,a.Iy.input,a.Iy.input,a.Iy.white,a.Iy.grayDeep,a.Iy.white,a.Iy.sectiontitle,a.Iy.sectiontitle,a.Iy.white,a.Iy.sectiontitle),g=l.Z.ul(r||(r=(0,d.Z)(["\n  width: 100%;\n  padding-bottom: 30px;\n  li {\n    width: 100%;\n    padding-bottom: 30px;\n    :first-of-type {\n      display: flex;\n      justify-content: space-between;\n      padding-top: 40px;\n      > button {\n        width: 225px;\n        height: 40px;\n        font-weight: 500;\n        color: #fff;\n        background: ",";\n        border: 1px solid ",";\n        border-radius: 6px;\n        cursor: pointer;\n      }\n    }\n    :last-of-type {\n      display: flex;\n      justify-content: space-between;\n      > div {\n        .portfolio-zero {\n          width: 690px;\n          height: 100px;\n          display: flex;\n          align-items: center;\n          justify-content: center;\n          > div {\n            width: 100%;\n            > span {\n              width: 100%;\n            }\n          }\n        }\n        .portfolio-box {\n          width: 690px;\n          height: 100px;\n          border: 1px solid ",";\n          border-radius: 6px;\n          margin-bottom: 14px;\n          > div {\n            width: 100%;\n            height: 40px;\n            line-height: 40px;\n            background: ",";\n            display: flex;\n            align-items: center;\n            justify-content: space-between;\n            > div {\n              display: flex;\n              align-items: center;\n              padding: 0 13px 0 10px;\n              a {\n                color: ",";\n                font-size: 14px;\n                font-weight: 400;\n                margin-left: 5px;\n              }\n              p {\n                font-size: 18px;\n                height: 21px;\n                line-height: 21px;\n              }\n            }\n          }\n          .portfolio-icons {\n            display: flex;\n            gap: 15px;\n            font-size: 21px;\n            line-height: 21px;\n          }\n          .delete-icon {\n            font-size: 21px;\n            line-height: 21px;\n            cursor: pointer;\n          }\n          > span {\n            display: block;\n            padding-left: 5px;\n            font-size: 14px;\n            font-weight: 400;\n            padding: 10px;\n          }\n        }\n      }\n    }\n  }\n"])),a.Iy.btn,a.Iy.btn,a.Iy.search,a.Iy.search,a.Iy.black),f=l.Z.div(s||(s=(0,d.Z)(["\n  /* padding: 30px 25px 0px 92px; */\n  height: 100%;\n  overflow-y: hidden;\n  width: 1651px;\n  padding-left: 92px;\n  padding-right: 100px;\n\n  .portfolio-list {\n    width: 100%;\n    height: 695px;\n\n    padding-bottom: 30px;\n\n    > li {\n      h2 {\n        font-size: 24px;\n        font-weight: 700;\n        letter-spacing: -0.96px;\n        margin-top: 30px;\n        margin-bottom: 30px;\n      }\n      :first-of-type {\n        display: flex;\n        justify-content: space-between;\n        padding-top: 40px;\n        > button {\n          margin-top: 70px;\n          margin-bottom: 20px;\n          width: 225px;\n          height: 40px;\n          font-weight: 500;\n          color: #fff;\n          background: ",";\n          border: 1px solid ",";\n          border-radius: 6px;\n          cursor: pointer;\n        }\n      }\n      :last-of-type {\n        display: flex;\n        justify-content: space-between;\n        gap: 40px;\n        > div {\n          .portfolio-zero {\n            width: 690px;\n            height: 100px;\n            display: flex;\n            align-items: center;\n            justify-content: center;\n            > div {\n              width: 100%;\n              > span {\n                width: 100%;\n              }\n            }\n          }\n          .portfolio-box {\n            display: flex;\n            gap: 10px;\n            margin-bottom: 14px;\n            .portfolio-inner {\n              width: 650px;\n              display: flex;\n              flex-direction: column;\n              border: 1px solid ",";\n              border-radius: 6px;\n              .portfolio-top {\n                display: flex;\n                justify-content: space-between;\n                align-items: center;\n                background: ",";\n                height: 40px;\n                div {\n                  :first-of-type {\n                    display: flex;\n                    align-items: center;\n                    gap: 3px;\n                    padding-left: 10px;\n                  }\n                  :last-of-type {\n                    display: flex;\n                    align-items: center;\n                    gap: 10px;\n                    padding-right: 10px;\n                    .main-pofol-icon {\n                      width: 20px;\n                      font-size: 18px;\n                      color: ",";\n                    }\n                    .delete-icon {\n                      font-size: 18px;\n                      color: ",";\n                    }\n                  }\n                }\n              }\n              .portfolio-btm {\n                width: 690px;\n                height: 60px;\n                padding: 10px;\n              }\n            }\n            .main-check {\n              line-height: 45px;\n            }\n          }\n        }\n      }\n    }\n  }\n  .buttons {\n    border-top: 1px solid ",";\n    display: flex;\n    height: 130px;\n    width: 100%;\n    div {\n      margin: 0 auto;\n      > button {\n        margin: 40px 0;\n        width: 131px;\n        height: 50px;\n        color: #fff;\n        background: ",";\n        border: none;\n        border-radius: 4px;\n        cursor: pointer;\n      }\n    }\n  }\n"])),a.Iy.btn,a.Iy.btn,a.Iy.search,a.Iy.search,a.Iy.admintxt,a.Iy.btn,a.Iy.grayLight,a.Iy.input)},4261:(n,e,t)=>{t.d(e,{Z:()=>l});const i={randomUUID:"undefined"!==typeof crypto&&crypto.randomUUID&&crypto.randomUUID.bind(crypto)};let p;const o=new Uint8Array(16);function r(){if(!p&&(p="undefined"!==typeof crypto&&crypto.getRandomValues&&crypto.getRandomValues.bind(crypto),!p))throw new Error("crypto.getRandomValues() not supported. See https://github.com/uuidjs/uuid#getrandomvalues-not-supported");return p(o)}const s=[];for(let a=0;a<256;++a)s.push((a+256).toString(16).slice(1));function d(n){let e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:0;return s[n[e+0]]+s[n[e+1]]+s[n[e+2]]+s[n[e+3]]+"-"+s[n[e+4]]+s[n[e+5]]+"-"+s[n[e+6]]+s[n[e+7]]+"-"+s[n[e+8]]+s[n[e+9]]+"-"+s[n[e+10]]+s[n[e+11]]+s[n[e+12]]+s[n[e+13]]+s[n[e+14]]+s[n[e+15]]}const l=function(n,e,t){if(i.randomUUID&&!e&&!n)return i.randomUUID();const p=(n=n||{}).random||(n.rng||r)();if(p[6]=15&p[6]|64,p[8]=63&p[8]|128,e){t=t||0;for(let n=0;n<16;++n)e[t+n]=p[n];return e}return d(p)}}}]);
//# sourceMappingURL=649.7458fa6a.chunk.js.map