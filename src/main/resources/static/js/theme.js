(function ($) {
  "use strict";

  if ($(".accrodion-grp").length) {
    var accrodionGrp = $(".accrodion-grp");
    accrodionGrp.each(function () {
      var accrodionName = $(this).data("grp-name");
      var Self = $(this);
      var accordion = Self.find(".accrodion");
      Self.addClass(accrodionName);
      Self.find(".accrodion .accrodion-content").hide();
      Self.find(".accrodion.active").find(".accrodion-content").show();
      accordion.each(function () {
        $(this)
          .find(".accrodion-title")
          .on("click", function () {
            if ($(this).parent().hasClass("active") === false) {
              $(".accrodion-grp." + accrodionName)
                .find(".accrodion")
                .removeClass("active");
              $(".accrodion-grp." + accrodionName)
                .find(".accrodion")
                .find(".accrodion-content")
                .slideUp();
              $(this).parent().addClass("active");
              $(this).parent().find(".accrodion-content").slideDown();
            }
          });
      });
    });
  }

  if ($(".contact-form-validated").length) {
    $(".contact-form-validated").validate({
      // initialize the plugin
      rules: {
        name: {
          required: true,
        },
        email: {
          required: true,
          email: true,
        },
        message: {
          required: true,
        },
        subject: {
          required: true,
        },
      },
      submitHandler: function (form) {
        // sending value with ajax request
        $.post(
          $(form).attr("action"),
          $(form).serialize(),
          function (response) {
            $(form).parent().find(".result").append(response);
            $(form).find('input[type="text"]').val("");
            $(form).find('input[type="email"]').val("");
            $(form).find("textarea").val("");
            console.log(response);
          }
        );
        return false;
      },
    });
  }

  if ($(".mc-form").length) {
    $(".mc-form").each(function () {
      var Self = $(this);
      var mcURL = Self.data("url");
      var mcResp = Self.parent().find(".mc-form__response");

      Self.ajaxChimp({
        url: mcURL,
        callback: function (resp) {
          // appending response
          mcResp.append(function () {
            return '<p class="mc-message">' + resp.msg + "</p>";
          });
          // making things based on response
          if (resp.result === "success") {
            // Do stuff
            Self.removeClass("errored").addClass("successed");
            mcResp.removeClass("errored").addClass("successed");
            Self.find("input").val("");

            mcResp.find("p").fadeOut(10000);
          }
          if (resp.result === "error") {
            Self.removeClass("successed").addClass("errored");
            mcResp.removeClass("successed").addClass("errored");
            Self.find("input").val("");

            mcResp.find("p").fadeOut(10000);
          }
        },
      });
    });
  }

  if ($(".stricky").length) {
    $(".stricky")
      .addClass("original")
      .clone(true)
      .insertAfter(".stricky")
      .addClass("stricked-menu")
      .removeClass("original");
  }
  if ($(".main-navigation .navigation-box").length) {
    var subMenu = $(".main-navigation .sub-menu");
    subMenu
      .parent("li")
      .children("a")
      .append(function () {
        return '<button class="sub-nav-toggler"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>';
      });
    var mainNavToggler = $(".header-navigation .menu-toggler");
    var subNavToggler = $(".main-navigation .sub-nav-toggler");
    mainNavToggler.on("click", function () {
      var Self = $(this);
      var menu = Self.data("target");
      $(menu).slideToggle();
      $(menu).toggleClass("showen");
      return false;
    });
    subNavToggler.on("click", function () {
      var Self = $(this);
      Self.parent().parent().children(".sub-menu").slideToggle();
      return false;
    });
  }
  if ($(".scroll-to-target").length) {
    $(".scroll-to-target").on("click", function () {
      var target = $(this).attr("data-target");
      // animate
      $("html, body").animate(
        {
          scrollTop: $(target).offset().top,
        },
        1000
      );

      return false;
    });
  }
  if ($(".wow").length) {
    var wow = new WOW({
      boxClass: "wow", // animated element css class (default is wow)
      animateClass: "animated", // animation css class (default is animated)
      offset: 250, // distance to the element when triggering the animation (default is 0)
      mobile: true, // trigger animations on mobile devices (default is true)
      live: true, // act on asynchronously loaded content (default is true)
    });
    wow.init();
  }

  function SmoothMenuScroll() {
    var anchor = $(".scrollToLink");
    if (anchor.length) {
      anchor.children("a").bind("click", function (event) {
        if ($(window).scrollTop() > 10) {
          var headerH = "67";
        } else {
          var headerH = "100";
        }
        var target = $(this);
        $("html, body")
          .stop()
          .animate(
            {
              scrollTop: $(target.attr("href")).offset().top - headerH + "px",
            },
            1200,
            "easeInOutExpo"
          );
        anchor.removeClass("current");
        target.parent().addClass("current");
        event.preventDefault();
      });
    }
  }
  SmoothMenuScroll();

  function OnePageMenuScroll() {
    var windscroll = $(window).scrollTop();
    if (windscroll >= 100) {
      var menuAnchor = $(".one-page-scroll-menu .scrollToLink").children("a");
      menuAnchor.each(function () {
        // grabing section id dynamically
        var sections = $(this).attr("href");
        $(sections).each(function () {
          // checking is scroll bar are in section
          if ($(this).offset().top <= windscroll + 100) {
            // grabing the dynamic id of section
            var Sectionid = $(sections).attr("id");
            // removing current class from others
            $(".one-page-scroll-menu").find("li").removeClass("current");
            // adding current class to related navigation
            $(".one-page-scroll-menu")
              .find("a[href*=\\#" + Sectionid + "]")
              .parent()
              .addClass("current");
          }
        });
      });
    } else {
      $(".one-page-scroll-menu li.current").removeClass("current");
      $(".one-page-scroll-menu li:first").addClass("current");
    }
  }
  if ($(".search-popup__toggler").length) {
    $(".search-popup__toggler").on("click", function (e) {
      $(".search-popup").addClass("active");
      e.preventDefault();
    });
  }

  if ($(".search-popup__overlay").length) {
    $(".search-popup__overlay").on("click", function (e) {
      $(".search-popup").removeClass("active");
      e.preventDefault();
    });
  }
  if ($(".counter").length) {
    $(".counter").counterUp({
      delay: 10,
      time: 3000,
    });
  }

  if ($(".side-menu__toggler").length) {
    $(".side-menu__toggler").on("click", function (e) {
      $(".side-menu__block").addClass("active");
      e.preventDefault();
    });
  }

  if ($(".side-menu__block-overlay").length) {
    $(".side-menu__block-overlay").on("click", function (e) {
      $(".side-menu__block").removeClass("active");
      e.preventDefault();
    });
  }

  if ($(".img-popup").length) {
    var groups = {};
    $(".img-popup").each(function () {
      var id = parseInt($(this).attr("data-group"), 10);

      if (!groups[id]) {
        groups[id] = [];
      }

      groups[id].push(this);
    });

    $.each(groups, function () {
      $(this).magnificPopup({
        type: "image",
        closeOnContentClick: true,
        closeBtnInside: false,
        gallery: { enabled: true },
      });
    });
  }

  if ($('[data-toggle="tooltip"]').length) {
    $('[data-toggle="tooltip"]').tooltip();
  }

  if ($(".video-popup").length) {
    $(".video-popup").magnificPopup({
      disableOn: 700,
      type: "iframe",
      mainClass: "mfp-fade",
      removalDelay: 160,
      preloader: true,

      fixedContentPos: false,
    });
  }

  if ($(".faq-one__single").length) {
    var faqCount = $(".faq-one__single").length;
    $(".faq-one")
      .find("[class*=col-]")
      .eq(faqCount - 2)
      .css({
        "padding-bottom": "0",
        "border-bottom": "0",
      });
    $(".faq-one")
      .find("[class*=col-]")
      .eq(faqCount - 1)
      .css({
        "padding-bottom": "0",
        "border-bottom": "0",
      });
  }

  if ($(".typed-effect").length) {
    $(".typed-effect").each(function () {
      var typedStrings = $(this).data("strings");
      var typedTag = $(this).attr("id");
      var typed = new Typed("#" + typedTag, {
        typeSpeed: 100,
        backSpeed: 100,
        fadeOut: true,
        loop: true,
        strings: typedStrings.split(","),
      });
    });
  }

  $(window).on("scroll", function () {
    if ($(".stricked-menu").length) {
      var headerScrollPos = 100;
      var stricky = $(".stricked-menu");
      if ($(window).scrollTop() > headerScrollPos) {
        stricky.addClass("stricky-fixed");
      } else if ($(this).scrollTop() <= headerScrollPos) {
        stricky.removeClass("stricky-fixed");
      }
    }
    OnePageMenuScroll();
    if ($(".scroll-to-top").length) {
      var strickyScrollPos = 100;
      if ($(window).scrollTop() > strickyScrollPos) {
        $(".scroll-to-top").fadeIn(500);
      } else if ($(this).scrollTop() <= strickyScrollPos) {
        $(".scroll-to-top").fadeOut(500);
      }
    }
  });
  $(window).on("load", function () {
    if ($(".testimonials-two__thumb-carousel").length) {
      var testimonialsTwoThumbCarousel = new Swiper(
        ".testimonials-two__thumb-carousel",
        {
          slidesPerView: 4,
          spaceBetween: 30,
          speed: 1400,
          watchSlidesVisibility: true,
          watchSlidesProgress: true,
          loop: true,
          autoplay: {
            delay: 5000,
          },
        }
      );
    }

    if ($(".testimonials-two__carousel").length) {
      var testimonialsTwoCarousel = new Swiper(".testimonials-two__carousel", {
        observer: true,
        observeParents: true,
        speed: 1400,
        mousewheel: false,
        autoplay: {
          delay: 5000,
        },
        thumbs: {
          swiper: testimonialsTwoThumbCarousel,
        },
      });
    }

    if ($(".video-one__carousel").length) {
      $(".video-one__carousel").owlCarousel({
        loop: true,
        margin: 0,
        nav: true,
        navText: [
          '<i class="potisen-icon-right-arrow rotated"></i>',
          '<i class="potisen-icon-right-arrow"></i>',
        ],
        dots: false,
        autoWidth: false,
        autoplay: true,
        smartSpeed: 700,
        autoplayTimeout: 5000,
        autoplayHoverPause: true,
        responsive: {
          0: {
            items: 1,
          },
          480: {
            items: 1,
          },
          600: {
            items: 1,
          },
          991: {
            items: 1,
          },
          1000: {
            items: 1,
          },
          1200: {
            items: 1,
          },
        },
      });
    }
    if ($(".social-shares__twitter-carousel").length) {
      $(".social-shares__twitter-carousel").owlCarousel({
        loop: true,
        margin: 0,
        nav: false,
        dots: true,
        autoWidth: false,
        autoplay: true,
        smartSpeed: 700,
        autoplayTimeout: 5000,
        autoplayHoverPause: true,
        responsive: {
          0: {
            items: 1,
          },
          480: {
            items: 1,
          },
          600: {
            items: 1,
          },
          991: {
            items: 1,
          },
          1000: {
            items: 1,
          },
          1200: {
            items: 1,
          },
        },
      });
    }
    if ($(".testimonials-one__carousel").length) {
      $(".testimonials-one__carousel").owlCarousel({
        loop: true,
        margin: 0,
        nav: false,
        dots: true,
        autoWidth: false,
        autoplay: true,
        smartSpeed: 700,
        autoplayTimeout: 5000,
        autoplayHoverPause: true,
        responsive: {
          0: {
            items: 1,
          },
          480: {
            items: 1,
          },
          600: {
            items: 1,
          },
          991: {
            items: 1,
          },
          1000: {
            items: 1,
          },
          1200: {
            items: 1,
          },
        },
      });
    }

    if ($(".brand-one__carousel").length) {
      $(".brand-one__carousel").owlCarousel({
        loop: true,
        margin: 115,
        nav: false,
        dots: false,
        autoWidth: false,
        autoplay: true,
        smartSpeed: 700,
        autoplayTimeout: 5000,
        autoplayHoverPause: true,
        responsive: {
          0: {
            items: 2,
            margin: 30,
          },
          480: {
            items: 3,
            margin: 30,
          },
          600: {
            items: 3,
            margin: 30,
          },
          991: {
            items: 5,
            margin: 30,
          },
          1000: {
            items: 5,
          },
          1200: {
            items: 5,
          },
        },
      });
    }

    if ($(".banner-carousel__one").length) {
      $(".banner-carousel__one").owlCarousel({
        loop: true,
        items: 1,
        margin: 0,
        dots: true,
        nav: false,
        animateOut: "slideOutDown",
        animateIn: "fadeIn",
        active: true,
        smartSpeed: 1000,
        autoplay: 7000,
      });
      $(".banner-carousel-btn__left-btn").on("click", function () {
        $(".banner-carousel__one").trigger("next.owl.carousel");
        return false;
      });
      $(".banner-carousel-btn__right-btn").on("click", function () {
        $(".banner-carousel__one").trigger("prev.owl.carousel");
        return false;
      });
    }
    if ($(".custom-cursor__overlay").length) {
      // / cursor /
      var cursor = $(".custom-cursor__overlay .cursor"),
        follower = $(".custom-cursor__overlay .cursor-follower");

      var posX = 0,
        posY = 0;

      var mouseX = 0,
        mouseY = 0;

      TweenMax.to({}, 0.016, {
        repeat: -1,
        onRepeat: function () {
          posX += (mouseX - posX) / 9;
          posY += (mouseY - posY) / 9;

          TweenMax.set(follower, {
            css: {
              left: posX - 22,
              top: posY - 22,
            },
          });

          TweenMax.set(cursor, {
            css: {
              left: mouseX,
              top: mouseY,
            },
          });
        },
      });

      $(document).on("mousemove", function (e) {
        var scrollTop =
          window.pageYOffset || document.documentElement.scrollTop;
        mouseX = e.pageX;
        mouseY = e.pageY - scrollTop;
      });
      $("button, a").on("mouseenter", function () {
        cursor.addClass("active");
        follower.addClass("active");
      });
      $("button, a").on("mouseleave", function () {
        cursor.removeClass("active");
        follower.removeClass("active");
      });
      $(".custom-cursor__overlay").on("mouseenter", function () {
        cursor.addClass("close-cursor");
        follower.addClass("close-cursor");
      });
      $(".custom-cursor__overlay").on("mouseleave", function () {
        cursor.removeClass("close-cursor");
        follower.removeClass("close-cursor");
      });
    }
    if ($(".preloader").length) {
      $(".preloader").fadeOut();
    }

    if ($(".countdown-one__list").length) {
      $(".countdown-one__list").countdown({
        date: "August 11, 2022 00:00:00",
        render: function (date) {
          this.el.innerHTML =
            "<li> <div class='days'> <i>" +
            date.days +
            "</i> <span>Days</span> </div> </li>" +
            "<li> <div class='hours'> <i>" +
            date.hours +
            "</i> <span>Hours</span> </div> </li>" +
            "<li> <div class='minutes'> <i>" +
            date.min +
            "</i> <span>Minutes</span> </div> </li>" +
            "<li> <div class='seconds'> <i>" +
            date.sec +
            "</i> <span>Seconds</span> </div> </li>";
        },
      });
    }

    if ($(".banner-carousel__one").length) {
      var myCarousel = $(".banner-carousel__one");
      var singleItem = ".banner-one__slide";
      var nextBtn = $(".banner-one__nav-left");
      var prevBtn = $(".banner-one__nav-right");

      if (myCarousel.data("carousel-in-anim") !== undefined) {
        var carouselInAnim = myCarousel.data("carousel-in-anim");
      } else {
        var carouselInAnim = "fadeIn";
      }

      if (myCarousel.data("carousel-out-anim") !== undefined) {
        var carouselOutAnim = myCarousel.data("carousel-out-anim");
      } else {
        var carouselOutAnim = "slideOutDown";
      }

      // dynamic count for carousel
      myCarousel.owlCarousel({
        loop: true,
        items: 1,
        margin: 0,
        dots: true,
        nav: false,
        animateOut: carouselOutAnim,
        animateIn: carouselInAnim,
        smartSpeed: 1000,
        autoplay: 5000,
        autoplayHoverPause: true,
      });

      nextBtn.on("click", function () {
        myCarousel.trigger("next.owl.carousel", [500]);
        return false;
      });
      prevBtn.on("click", function () {
        myCarousel.trigger("prev.owl.carousel", [300]);
        return false;
      });
    }

    if ($(".static-banner-one__bg").length) {
      $(".static-banner-one__bg-inner").vegas({
        slides: [
          { src: "/images/background/banner-bg-1-1.jpg" },
          { src: "/images/background/banner-bg-1-3.jpg" },
          { src: "/images/background/India-2106x1406.webp" },
          { src: "/images/background/banner-bg-1-4.jpg" }
        ],
        transition: "slideUp",
        timer: false,
      });
    }
  });
})(jQuery);
