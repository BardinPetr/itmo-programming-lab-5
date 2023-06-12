import multiprocessing as mp
import re
import sys
import translators as ts

BLACKLIST = {"dateFormat"}
BASE_LANG = "en"
TARGET_LANGS = ["ru"]


def build_source(package, base_name, contents):
    return f"""package {package};

import java.util.ListResourceBundle;

public class {base_name} extends ListResourceBundle {{
    @Override
    protected Object[][] getContents() {{
        return new Object[][] {{
{contents}
        }};
    }}
}}"""


def translate(data):
    text, to = data

    if text in BLACKLIST:
        return text

    try:
        res = ts.translate_text(text, from_language=BASE_LANG, to_language=to)
        return res if len(res) else text
    except:
        return text


def save_file(base_path, class_name, package, locale, data):
    name = f"{class_name}_{locale}"
    target_file = f"{base_path}{name}.java"
    with open(target_file, 'w') as target_file:
        contents_str = [f'{12 * " "}{{"{k}", "{v}"}}' for k, v in data]
        contents_str = ',\n'.join(contents_str)
        text = build_source(package, name, contents_str)
        target_file.write(text)


def main():
    src_file = sys.argv[1]
    dst_dir = re.sub(r'\w+\.java', '', src_file)

    with open(src_file, "r") as f:
        source_bundle = f.read()

    package = re.findall(r"package\s+([.\w]+)", source_bundle)[0]
    class_name = re.findall(r"class\s+([.\w]+)", source_bundle)[0]
    data = re.findall(r"{\s*\"(.*)\"\s*,\s*\"(.*)\"\s*}", source_bundle)
    texts = {i[0]: i[1] for i in data}

    for target in TARGET_LANGS:
        with mp.Pool(processes=8) as pool:
            res = pool.map(translate, zip(texts.values(), [target] * len(texts)))
            save_file(dst_dir, class_name, package, target, zip(texts.keys(), res))


if __name__ == '__main__':
    main()
