"use strict";
(self.webpackChunkgreenjobgo_admin =
  self.webpackChunkgreenjobgo_admin || []).push([
  [947],
  {
    1947: (n, e, i) => {
      i.r(e), i.d(e, { default: () => m });
      var t,
        s,
        l = i(2791),
        o = i(168),
        a = i(456),
        d = i(2291);
      const r = a.Z.div(
          t || (t = (0, o.Z)(["\n  width: 100%;\n  height: 100vh;\n"])),
        ),
        c = a.Z.ul(
          s ||
            (s = (0, o.Z)([
              "\n  width: 100%;\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n  li {\n    :first-of-type {\n      width: 50%;\n      height: 100vh;\n      img {\n        width: 100%;\n        height: 100vh;\n      }\n    }\n    :last-of-type {\n      display: flex;\n      justify-content: flex-end;\n      align-items: center;\n      flex-direction: column;\n      gap: 30px;\n      margin-right: 12.5%;\n      .login-title {\n        padding-left: 65px;\n      }\n      form {\n        display: flex;\n        justify-content: flex-end;\n        align-items: center;\n        flex-direction: column;\n        gap: 25px;\n        div {\n          width: 100%;\n          display: flex;\n          justify-content: flex-end;\n          align-items: center;\n          gap: 20px;\n          font-weight: 600;\n          input {\n            width: 440px;\n            height: 40px;\n            border: 1px solid black;\n            border-radius: 6px;\n            padding: 0 5px;\n          }\n          button {\n            width: 440px;\n            height: 50px;\n            font-size: 16px;\n            font-weight: 600;\n            color: ",
              ";\n            background: ",
              ";\n            border: 1px solid ",
              ";\n            border-radius: 6px;\n            cursor: pointer;\n          }\n        }\n      }\n    }\n  }\n",
            ])),
          d.Iy.white,
          d.Iy.admintxt,
          d.Iy.admintxt,
        );
      var h = i(361),
        g = i(7689),
        p = i(6030),
        x = i(4151),
        u = i(3508),
        f = i(184);
      const m = () => {
        const [n, e] = (0, l.useState)("greendg01"),
          [i, t] = (0, l.useState)("green1234"),
          [s, o] = (0, l.useState)(""),
          [a, d] = (0, l.useState)(!1),
          [m, j] = (0, l.useState)(!1),
          [v, w] = (0, l.useState)(""),
          [y, b] = (0, p.FV)(x.q),
          k = (0, g.s0)();
        return (
          (0, l.useEffect)(() => {
            (0, h.av)(o), j(!!v);
          }, [v]),
          (0, f.jsxs)(r, {
            children: [
              (0, f.jsxs)(c, {
                children: [
                  (0, f.jsx)("li", {
                    children: (0, f.jsx)("img", {
                      src: "https://greenjobgo.kr".concat(s),
                      alt: "LoginImage",
                    }),
                  }),
                  (0, f.jsxs)("li", {
                    children: [
                      (0, f.jsx)("div", {
                        className: "login-title",
                        children: (0, f.jsx)("img", {
                          src: "../../assets/LoginTitle.png",
                          alt: "LoginTitle",
                        }),
                      }),
                      (0, f.jsxs)("form", {
                        children: [
                          (0, f.jsxs)("div", {
                            children: [
                              (0, f.jsx)("label", {
                                htmlFor: "login-id",
                                children: "\uc544\uc774\ub514",
                              }),
                              (0, f.jsx)("input", {
                                type: "text",
                                id: "login-id",
                                placeholder:
                                  "\uc544\uc774\ub514\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",
                                value: n,
                                onChange: n =>
                                  (n => {
                                    e(n.target.value);
                                  })(n),
                              }),
                            ],
                          }),
                          (0, f.jsxs)("div", {
                            children: [
                              (0, f.jsx)("label", {
                                htmlFor: "login-password",
                                children: "\ube44\ubc00\ubc88\ud638",
                              }),
                              (0, f.jsx)("input", {
                                type: "password",
                                id: "login-password",
                                placeholder:
                                  "\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",
                                value: i,
                                onChange: n =>
                                  (n => {
                                    t(n.target.value);
                                  })(n),
                              }),
                            ],
                          }),
                          (0, f.jsx)("div", {
                            children: (0, f.jsx)("button", {
                              onClick: async e => {
                                if ((e.preventDefault(), n))
                                  if (i)
                                    try {
                                      const {
                                        role: e,
                                        accessToken: t,
                                        refreshToken: s,
                                        id: l,
                                        name: o,
                                        accessTokenTime: a,
                                      } = await (0, h.XB)(n, i, w);
                                      "ROLE_ADMIN" === e && t
                                        ? (b({
                                            isLogin: !0,
                                            accessToken: t,
                                            role: e,
                                            id: l,
                                            name: o,
                                          }),
                                          k("/admin/home"),
                                          setTimeout(() => {
                                            s &&
                                              ((0, h.L2)(t, s),
                                              b({
                                                isLogin: !1,
                                                accessToken: null,
                                                role: "",
                                                id: "",
                                                name: "",
                                              }),
                                              k("/admin/"));
                                          }, a),
                                          console.log(a / 1e3))
                                        : k("/admin/");
                                    } catch (t) {
                                      w(t);
                                    }
                                  else
                                    w(
                                      "\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825 \ud574 \uc8fc\uc138\uc694.",
                                    );
                                else
                                  w(
                                    "\uc544\uc774\ub514\ub97c \uc785\ub825 \ud574 \uc8fc\uc138\uc694.",
                                  );
                              },
                              children: "\ub85c\uadf8\uc778",
                            }),
                          }),
                        ],
                      }),
                    ],
                  }),
                ],
              }),
              m &&
                (0, f.jsx)(u.Z, {
                  header: "",
                  open: m,
                  close: () => {
                    j(!1), w("");
                  },
                  onConfirm: () => {
                    j(!1), w("");
                  },
                  children: (0, f.jsx)("span", { children: v }),
                }),
            ],
          })
        );
      };
    },
  },
]);
//# sourceMappingURL=947.bd514cdd.chunk.js.map
