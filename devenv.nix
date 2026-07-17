{
  pkgs,
  lib,
  config,
  ...
}:
{
  # https://devenv.sh/languages/
  languages = {
    java = {
      enable = true;
      jdk.package = pkgs.openjdk25;
      gradle.enable = true;
    };
    kotlin.enable = true;
  };

  # See full reference at https://devenv.sh/reference/options/
}
